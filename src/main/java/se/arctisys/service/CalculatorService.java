package se.arctisys.service;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arctisys.constants.PropertyConstants;
import se.arctisys.constants.TradeConstants;
import se.arctisys.domain.Share;
import se.arctisys.domain.ShareDayRate;
import se.arctisys.domain.Transaction;
import se.arctisys.model.Profit;
import se.arctisys.repository.ShareDayRateRepository;
import se.arctisys.repository.ShareRepository;
import se.arctisys.util.Util;

@Service
public class CalculatorService {

    private ShareRepository shareRepo;
    private ShareDayRateRepository dayRateRepo;
	private PropertyService propService;

	private static final Logger LOG = LoggerFactory.getLogger(CalculatorService.class);
	
	protected Double getMovingAvarage(Share share, Date startDate, Date endDate) throws ParseException {
		Double result = 0.0;		
		Integer counter = 0;
		Double stockSum = 0.0;

		for (ShareDayRate rate : share.getDayRates()) {
			if (rate.getActualDate().after(startDate) && rate.getActualDate().before(endDate)) {
				if (rate.getSellRate() > 0) {
					counter++;
					stockSum = stockSum + rate.getSellRate();
				}
			}
			if (rate.getActualDate().before(startDate)) {
				break;
			}			
		}
		
		result = Util.round(stockSum / counter, 2);
		
		return result;
	}

	public ShareDayRate setMovingAverages(ShareDayRate rate, Share share) throws ParseException {
		Long daysMovingAvShort = propService.getLong(PropertyConstants.MOVING_AVERAGE_SHORT);
		Long daysMovingAvMedium = propService.getLong(PropertyConstants.MOVING_AVERAGE_MEDIUM);
		Long daysMovingAvLong = propService.getLong(PropertyConstants.MOVING_AVERAGE_LONG);
		rate.setMovingAverageShort(getMovingAvarage(share, Util.getDateByDaysBack(daysMovingAvShort, rate.getActualDate()), rate.getActualDate()));
		rate.setMovingAverageMedium(getMovingAvarage(share, Util.getDateByDaysBack(daysMovingAvMedium, rate.getActualDate()), rate.getActualDate()));
		rate.setMovingAverageLong(getMovingAvarage(share, Util.getDateByDaysBack(daysMovingAvLong, rate.getActualDate()), rate.getActualDate()));
		rate.setShare(share);
		return rate;
	}
	
	public void updateMovingAverages(String shareId) throws ParseException {
		Share share = shareRepo.findOne(shareId);
		for (ShareDayRate rate : share.getDayRates()) {
			rate = setMovingAverages(rate, share);
			dayRateRepo.save(rate);
		}
	}

	public Double getHighFrequence(Share share, Date startDate, Date endDate) throws ParseException {
		Double result = 0.0;		
		for (ShareDayRate rate : share.getDayRates()) {
			if (rate.getActualDate().after(startDate) && rate.getActualDate().before(endDate)) {
				if (result == 0.0 || result < rate.getMaxRate()) {
					result = rate.getMaxRate();
				}
			}			
			if (rate.getActualDate().before(startDate)) {
				break;
			}

		}
		
		return result;
	}

	public Double getLowFrequence(Share share, Date startDate, Date endDate) throws ParseException {
		Double result = 0.0;		
		for (ShareDayRate rate : share.getDayRates()) {
			if (rate.getActualDate().after(startDate) && rate.getActualDate().before(endDate)) {
				if (result == 0.0 || result > rate.getMinRate()) {
					result = rate.getMinRate();
				}
			}
			if (rate.getActualDate().before(startDate)) {
				break;
			}
		}
		
		return result;
	}

	public Profit getProfitBetweenDates(Date startDate, Date endDate, Share share, Integer amount) {
		Profit profit = new Profit();
		String nextTransactionType = TradeConstants.TRANSACTION_TYPE_BUY;
		Integer noOfShares = 0;
		if (share != null) {
			for (ShareDayRate dayRate : share.getDayRates()) {
				/* Moved stuff, commented for now 20171028 
				 
					if (nextTransactionType.equals(TradeConstants.TRANSACTION_TYPE_BUY)) {
						if (dayRate.getBuyRate() <= dayRate.getLowFrequencyRate()) {
							noOfShares = amount / dayRate.getBuyRate().intValue();
							profit.getTransactions().add(getTrans(dayRate, TradeConstants.TRANSACTION_TYPE_BUY, noOfShares));
							nextTransactionType = TradeConstants.TRANSACTION_TYPE_SELL;
						}
					} else if (nextTransactionType.equals(TradeConstants.TRANSACTION_TYPE_SELL)) {
						if (dayRate.getSellRate() >= dayRate.getHighFrequencyRate()) {
							profit.getTransactions().add(getTrans(dayRate, TradeConstants.TRANSACTION_TYPE_SELL, noOfShares));
							nextTransactionType = TradeConstants.TRANSACTION_TYPE_BUY;
						}						
					}
				}
				*/
			}
			Integer size = profit.getTransactions().size();
			if (size > 1) {
				// If last was sell (odd number of records), remove it.
				if (size % 2 != 0) {
					profit.getTransactions().remove(size - 1);
				}
				// Calculate result
				Double totalBuyAmount = 0.0;
				Double totalSellAmount = 0.0;
				for (Transaction trans : profit.getTransactions()) {
					if (trans.getType().equals(TradeConstants.TRANSACTION_TYPE_BUY)) {
						totalBuyAmount = totalBuyAmount + trans.getAmount();
					} else if (trans.getType().equals(TradeConstants.TRANSACTION_TYPE_SELL)) {
						totalSellAmount = totalSellAmount + trans.getAmount();
					}
				}
				profit.setResult(totalSellAmount - totalBuyAmount);
				profit.setTotalBuy(totalBuyAmount);
				profit.setTotalSell(totalSellAmount);
				profit.setStartDate(startDate);
				profit.setEndDate(startDate);
			}
			
		}
		return profit;
	}
	
	private Transaction getTrans(ShareDayRate dayRate, String transactionType, Long noOfShares) {
		Transaction trans = new Transaction();
		trans.setAmount(noOfShares * dayRate.getBuyRate());
		trans.setNoOfShares(noOfShares);
		trans.setTransactionDate(dayRate.getActualDate());
		trans.setType(transactionType);
		return trans;
	}
	@Autowired
	public void setShareRepo(ShareRepository shareRepo) {
		this.shareRepo = shareRepo;
	}
	@Autowired
	public void setShareDayRateRepo(ShareDayRateRepository dayRateRepo) {
		this.dayRateRepo = dayRateRepo;
	}
	@Autowired
	public void setPropService(PropertyService propService) {
		this.propService = propService;
	}
}
