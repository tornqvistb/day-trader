package se.arctisys.service;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arctisys.constants.PropertyConstants;
import se.arctisys.constants.TradeConstants;
import se.arctisys.domain.Account;
import se.arctisys.domain.Email;
import se.arctisys.domain.ShareDayRate;
import se.arctisys.domain.ShareTransaction;
import se.arctisys.domain.ShareHolding;
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
	
	private static final Logger LOG = LoggerFactory.getLogger(CalculatorService.class);
	
	public void checkForSignals() throws ParseException {
		// Check if time has passed trading time (property 17.00)
		if (timeToTrade()) {
			
			// loop through the TradingUsers
			for (TradingUser user : userRepo.findAll()) {
				LOG.info("Time to trade");
				Email email = new Email();
				// loop through the shares
				for (UserShare userShare : user.getUserShares()) {
					LOG.info("Usershare: " + userShare.getTradingUser().getName() + ", " + userShare.getShare().getId());
					// for each share, check day rate for today
					ShareDayRate dayRate = userShare.getShare().getLastDayRate();
					if (dayRate != null && Util.isToday(dayRate.getActualDate())) {
						LOG.info("Dayrate today");
						//if we have holding of this share check if its time to sell
						/* Commented for now 20171028
						if (userShare.hasStockHolding() && dayRate.isSellCandidate()) {
							LOG.info("Sell candidate");
							email.addContent(doSell(userShare, dayRate, user));
						} else if (!userShare.hasStockHolding() && dayRate.isBuyCandidate()) {
							LOG.info("Buy candidate");
							email.addContent(doBuy(userShare, dayRate, user));
						}
						*/
					}
				}				
				// If mail has content, send mail to user
				if (email.hasContent()) {
					email.setReceiver(user.getEmail());
					email.setSender(propertyService.getString(PropertyConstants.MAIL_USERNAME));
					email.setSubject("Trading recommendations from ArctiSys Trader");
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
		Double amount = userShare.getShareHolding().getNumberOfShares() * dayRate.getSellRate();
		ShareTransaction transaction = new ShareTransaction();
		transaction.setActualDate(new Date());
		transaction.setAmount(amount);
		transaction.setType(TradeConstants.TRANSACTION_TYPE_SELL);
		transaction.setUserShare(userShare);
		transaction.setStatus(TradeConstants.TRANSACTION_STATUS_NEW);
		transactionRepo.save(transaction);								
		// delete stock holding record
		holdingRepo.delete(userShare.getShareHolding().getId());
		// Update account with incoming amount
		Account account = user.getAccount();
		account.increaseBalance(amount);
		account.setTradingUser(user);
		accountRepo.save(account);
		// Update user share
		userShare.setLastSellDate(new Date());
		userShare.setTradingUser(user);
		//TODO Save
		return "Recommendation: Sell " + userShare.getShare().getDescription()  + ", no of shares: " + userShare.getShareHolding().getNumberOfShares() + ", amount: " + amount;
	}

	
	private String doBuy(UserShare userShare, ShareDayRate dayRate, TradingUser user) {
		
		// Calculate how many shares to buy (based on share buy amount and money on account)
		Long amountToBuyFor = userShare.getBuyAmount();
		if (user.getAccount().getActualBalance() < amountToBuyFor) {
			amountToBuyFor = Math.round(user.getAccount().getActualBalance());
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
		LOG.info("Before save transaction");
		transactionRepo.save(transaction);												
		// create stock holding record
		ShareHolding holding = new ShareHolding();
		holding.setAmount(buyAmount);
		holding.setLastBuyRate(dayRate.getBuyRate());
		holding.setNumberOfShares(noOfShares);
		holding.setUserShare(userShare);
		LOG.info("Before save holding");
		holdingRepo.save(holding);
		// Update account with outgoing amount
		Account account = user.getAccount();
		account.decreaseBalance(buyAmount);
		account.setTradingUser(user);
		LOG.info("Before save account");
		accountRepo.save(account);	
		// Update user share
		userShare.setLastBuyDate(new Date());
		userShare.setTradingUser(user);
		//TODO Save
		// add content to mail																
		return "Recommendation: Buy " + userShare.getShare().getDescription() + ", no of shares: " + noOfShares + ", amount: " + buyAmount;
	}

}