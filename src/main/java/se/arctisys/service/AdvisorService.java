package se.arctisys.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arctisys.constants.PropertyConstants;
import se.arctisys.domain.Share;
import se.arctisys.domain.ShareDayRate;
import se.arctisys.repository.ShareRepository;
import se.arctisys.util.Util;

@Service
public class AdvisorService {

	@Autowired
	private PropertyService propertyService;
	@Autowired
	private ShareRepository shareRepo;
	
	public void checkForSignals() throws ParseException {
		// Check if time has passed trading time (property 17.00)
		String tradingTime = propertyService.getString(PropertyConstants.TRADING_TIME);
		if (Util.timeHasPassed(tradingTime)) {
			// Check that last trading is not today (property last-trading day)
			String lastTradeDate = propertyService.getString(PropertyConstants.LAST_TRADING_DATE);
			if (!Util.isToday(Util.stringToDate(lastTradeDate))) {
				// loop through the shares
				for (Share share : shareRepo.findAll()) {
					// for each share, check day rate for today
					ShareDayRate dayRate = share.getLastDayRate();
					if (dayRate != null && Util.isToday(dayRate.getActualDate())) {
						// if we have holding of this share check if its time to sell
						if (share.getStockHoldings().size() > 0 && dayRate.isSellCandidate()) {
							// If yes
							// Sell all shares
							// create transaction record
							// delete stock holding record
							// Update account with incoming amount
							// add content to mail							
						}
						// if we don't have holding of this share check if its time to buy
						// If yes
							// Calculate how many shares to buy (based on share buy amount and money on account)
							// create transaction record
							// create stock holding record
							// Update account with outgoing amount
							// add content to mail								
					}
				}
				// If mail has content, send mail
				// Update parameter last-trading day with today									
			}
		}
	}

	
}