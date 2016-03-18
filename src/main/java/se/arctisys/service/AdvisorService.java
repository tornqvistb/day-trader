package se.arctisys.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arctisys.constants.PropertyConstants;
import se.arctisys.constants.TradeConstants;
import se.arctisys.domain.Account;
import se.arctisys.domain.Email;
import se.arctisys.domain.ShareDayRate;
import se.arctisys.domain.ShareTransaction;
import se.arctisys.domain.StockHolding;
import se.arctisys.domain.TradingUser;
import se.arctisys.domain.UserShare;
import se.arctisys.repository.AccountRepository;
import se.arctisys.repository.EmailRepository;
import se.arctisys.repository.ShareTransactionRepository;
import se.arctisys.repository.StockHoldingRepository;
import se.arctisys.repository.TradingUserRepository;
import se.arctisys.util.Util;

@Service
public class AdvisorService {

	@Autowired
	private PropertyService propertyService;
	@Autowired
	private TradingUserRepository userRepo;
	@Autowired
	private ShareTransactionRepository transactionRepo;
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private StockHoldingRepository holdingRepo;
	@Autowired
	private EmailRepository emailRepo;
	
	
	public void checkForSignals() throws ParseException {
		// Check if time has passed trading time (property 17.00)
		if (timeToTrade()) {
			// loop through the TradingUsers
			for (TradingUser user : userRepo.findAll()) {
				Email email = new Email();
				// loop through the shares
				for (UserShare userShare : user.getUserShares()) {
					// for each share, check day rate for today
					ShareDayRate dayRate = userShare.getShare().getLastDayRate();
					if (dayRate != null && Util.isToday(dayRate.getActualDate())) {
						//if we have holding of this share check if its time to sell
						if (userShare.hasStockHolding() && dayRate.isSellCandidate()) {
							email.addContent(doSell(userShare, dayRate, user));
						} else if (!userShare.hasStockHolding() && dayRate.isBuyCandidate()) {
							email.addContent(doBuy(userShare, dayRate, user));	
						}
					}
				}				
				// If mail has content, send mail to user
				if (email.hasContent()) {
					email.setReceiver(user.getEmail());
					email.setSender(propertyService.getString(PropertyConstants.MAIL_USERNAME));
					email.setSubject("Köp/Sälj rekommendationer från ArctiSys Trader");
					emailRepo.save(email);
				}				
			}			
			// Update parameter last-trading day with today
			propertyService.updateStringValue(PropertyConstants.LAST_TRADING_DATE, Util.dateToString(new Date()));
		}
	}
	
	private boolean timeToTrade() throws ParseException {
		
		boolean result = false;
		// Check if time has passed trading time (property 17.00)
		String tradingTime = propertyService.getString(PropertyConstants.TRADING_TIME);
		if (Util.timeHasPassed(tradingTime)) {
			// Check that last trading is not today (property last-trading day)
			String lastTradeDate = propertyService.getString(PropertyConstants.LAST_TRADING_DATE);
			if (!Util.isToday(Util.stringToDate(lastTradeDate))) {
				result = true;
			}
		}
		return result;
	}
	
	private String doSell(UserShare userShare, ShareDayRate dayRate, TradingUser user) {
		Double amount = userShare.getMainStockHolding().getNumberOfShares() * dayRate.getSellRate();
		ShareTransaction transaction = new ShareTransaction();
		transaction.setActualDate(new Date());
		transaction.setAmount(amount);
		transaction.setType(TradeConstants.TRANSACTION_TYPE_SELL);
		transaction.setUserShare(userShare);
		transaction.setStatus(TradeConstants.TRANSACTION_STATUS_NEW);
		transactionRepo.save(transaction);								
		// delete stock holding record
		holdingRepo.delete(userShare.getMainStockHolding().getId());
		// Update account with incoming amount
		Account account = user.getMainAccount();
		account.increaseBalance(amount);
		account.setTradingUser(user);
		accountRepo.save(account);
		// Update user share
		userShare.setLastSellDate(new Date());
		userShare.setTradingUser(user);
		//TODO Save
		return "Rekommendation: Sälj " + userShare.getShare().getDescription()  + ", antal aktier: " + userShare.getMainStockHolding().getNumberOfShares() + ", belopp: " + amount;
	}

	
	private String doBuy(UserShare userShare, ShareDayRate dayRate, TradingUser user) {
		
		// Calculate how many shares to buy (based on share buy amount and money on account)
		Long amountToBuyFor = userShare.getBuyAmount();
		if (user.getMainAccount().getActualBalance() < amountToBuyFor) {
			amountToBuyFor = Math.round(user.getMainAccount().getActualBalance());
		}		
		Long noOfShares = Math.round(amountToBuyFor/dayRate.getBuyRate());		
		Double buyAmount = noOfShares * dayRate.getBuyRate();
		// create transaction record
		ShareTransaction transaction = new ShareTransaction();
		transaction.setActualDate(new Date());
		transaction.setAmount(buyAmount);
		transaction.setType(TradeConstants.TRANSACTION_TYPE_BUY);
		transaction.setUserShare(userShare);
		transaction.setStatus(TradeConstants.TRANSACTION_STATUS_NEW);
		transactionRepo.save(transaction);												
		// create stock holding record
		StockHolding holding = new StockHolding();
		holding.setAmount(buyAmount);
		holding.setLastBuyRate(dayRate.getBuyRate());
		holding.setNumberOfShares(noOfShares);
		holding.setUserShare(userShare);
		holdingRepo.save(holding);
		// Update account with outgoing amount
		Account account = user.getMainAccount();
		account.decreaseBalance(buyAmount);
		account.setTradingUser(user);
		accountRepo.save(account);	
		// Update user share
		userShare.setLastBuyDate(new Date());
		userShare.setTradingUser(user);
		//TODO Save
		// add content to mail																
		return "Rekommendation: Köp " + userShare.getShare().getDescription() + ", antal aktier: " + noOfShares + ", belopp: " + buyAmount;
	}

}