package se.arctisys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import se.arctisys.constants.TradeConstants;
import se.arctisys.domain.ShareDayRate;
import se.arctisys.domain.Strategy;
import se.arctisys.domain.UserShare;
import se.arctisys.util.Util;

@Service
public class StrategyService {

	private ShareDayRate dayRate;
	private Strategy strategy;
	private static final Logger LOG = LoggerFactory.getLogger(StrategyService.class);
	 
	/**
	 * Metod som tar UserShare som in-parameter.
	 * Om användaren har innehav, så sker koll om det är dags för sälj enligt aktuell strategi.
	 * Om användaren inte har innehav, så sker koll om det är dags för köp enligt aktuell strategi.
	 * 
	 * Returnerar BUY, SELL eller NO_ACTION
	 * 
	 */	
	public String checkForAction(UserShare userShare){
		String action = TradeConstants.NO_ACTION;
		dayRate = userShare.getShare().getLastDayRate();
		if (dayRate != null && Util.isToday(dayRate.getActualDate())) {
			strategy = userShare.getStrategy();
			if (userShare.hasShareHolding()) {
				if (timeToSell()) {
					action = TradeConstants.ACTION_SELL;
				}
			} else {
				if (timeToBuy()) {
					action = TradeConstants.ACTION_BUY;
				}
			}
		}
		return action;
	}
	
	private boolean timeToSell() {
		boolean result = true;
		if (strategy.getCompareLongToMedium()) {
			if (dayRate.getMovingAverageMedium() > dayRate.getMovingAverageLong() * strategy.getMultipleLongToMedium()) {
				// Passed check
			} else {
				LOG.debug("timeToSell, failed test medium > long");
				return false;
			}
			if (dayRate.getMovingAverageShort() > dayRate.getMovingAverageMedium() * strategy.getMultipleMediumToShort()) {
				// Passed check
			} else {
				LOG.debug("timeToSell, failed test short > medium");
				return false;
			}
			if (dayRate.getSellRate() > dayRate.getMovingAverageShort() * strategy.getMultipleShortToCurrent()) {
				// Passed check
			} else {
				LOG.debug("timeToSell, failed test current > short");
				return false;
			}
		}
		return result;
	}

	private boolean timeToBuy() {
		boolean result = true;
		if (strategy.getCompareLongToMedium()) {
			if (dayRate.getMovingAverageMedium() < dayRate.getMovingAverageLong() * strategy.getMultipleLongToMedium()) {
				// Passed check
			} else {
				LOG.debug("timeToBuy, failed test medium < long");
				return false;
			}
			if (dayRate.getMovingAverageShort() < dayRate.getMovingAverageMedium() * strategy.getMultipleMediumToShort()) {
				// Passed check
			} else {
				LOG.debug("timeToBuy, failed test short < medium");
				return false;
			}
			if (dayRate.getSellRate() < dayRate.getMovingAverageShort() * strategy.getMultipleShortToCurrent()) {
				// Passed check
			} else {
				LOG.debug("timeToBuy, failed test current < short");
				return false;
			}
		}
		return result;
	}
	
}
