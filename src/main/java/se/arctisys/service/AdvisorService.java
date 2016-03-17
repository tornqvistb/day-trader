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
import se.arctisys.domain.TradingUser;
import se.arctisys.domain.UserShare;
import se.arctisys.repository.AccountRepository;
import se.arctisys.repository.ShareRepository;
import se.arctisys.repository.ShareTransactionRepository;
import se.arctisys.repository.StockHoldingRepository;
import se.arctisys.repository.TradingUserRepository;
import se.arctisys.util.Util;

@Service
public class AdvisorService {

	@Autowired
	private PropertyService propertyService;
	@Autowired
	private ShareRepository shareRepo;
	@Autowired
	private TradingUserRepository userRepo;
	@Autowired
	private ShareTransactionRepository transactionRepo;
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private StockHoldingRepository holdingRepo;
	
	
	public void checkForSignals() throws ParseException {
		// Check if time has passed trading time (property 17.00)
		String tradingTime = propertyService.getString(PropertyConstants.TRADING_TIME);
		if (Util.timeHasPassed(tradingTime)) {
			// Check that last trading is not today (property last-trading day)
			String lastTradeDate = propertyService.getString(PropertyConstants.LAST_TRADING_DATE);
			if (!Util.isToday(Util.stringToDate(lastTradeDate))) {
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
								// If yes sell all shares
								// create transaction record
								Double amount = userShare.getStockHolding().getNumberOfShares() * dayRate.getSellRate();
								ShareTransaction transaction = new ShareTransaction();
								transaction.setActualDate(new Date());
								transaction.setAmount(amount);
								transaction.setType(TradeConstants.TRANSACTION_TYPE_SELL);
								transaction.setUserShare(userShare);
								transaction.setStatus(TradeConstants.TRANSACTION_STATUS_NEW);
								transactionRepo.save(transaction);								
								// delete stock holding record
								holdingRepo.delete(userShare.getStockHolding().getId());
								// Update account with incoming amount
								Account account = user.getAccount();
								account.increaseBalance(amount);
								account.setTradingUser(user);
								accountRepo.save(account);
								// add content to mail
								email.addContent("Rekommendation: Sälj " + userShare.getShare().getDescription());
							} else if (!userShare.hasStockHolding() && dayRate.isBuyCandidate()) {
								// If yes, buy shares
								// Calculate how many shares to buy (based on share buy amount and money on account)
								// create transaction record
								// create stock holding record
								// Update account with outgoing amount
								// add content to mail																
							}
						}
					}
					// If mail has content, send mail to user
				}
				// Update parameter last-trading day with today						
			}
		}
	}
	
	private String doSell() {
		
		return "";
	}

	
}