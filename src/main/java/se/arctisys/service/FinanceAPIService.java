package se.arctisys.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import se.arctisys.constants.PropertyConstants;
import se.arctisys.constants.TradeConstants;
import se.arctisys.domain.Share;
import se.arctisys.domain.ShareDayRate;
import se.arctisys.domain.ShareOnMarket;
import se.arctisys.repository.ShareDayRateRepository;
import se.arctisys.repository.ShareOnMarketRepository;
import se.arctisys.repository.ShareRepository;
import se.arctisys.util.Util;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes2.QueryInterval;
import yahoofinance.query2v8.HistQuotesQuery2V8Request;
import yahoofinance.quotes.stock.StockQuote;

/**
 * Created by Björn Törnqvist, ArctiSys AB, 2016-02
 */
@Service
public class FinanceAPIService {

	private static final Logger LOG = LoggerFactory.getLogger(FinanceAPIService.class);

	private PropertyService propService;
	private ShareRepository shareRepo;
	private ShareDayRateRepository dayRateRepo;
	@Autowired
	private ShareOnMarketRepository somRepo;
	private CalculatorService calculatorService;


	public void readHistory() throws ParseException, IOException {
		LOG.info("Looking for history!");
		List<ShareOnMarket> sharesToImport = somRepo.findSOMByStatus(TradeConstants.SHARE_STATUS_START_IMPORT);
		for (ShareOnMarket som : sharesToImport) {
			String shareId =  som.getId();
			LOG.info("Share Id : " + shareId);
			Share share = shareRepo.findOne(shareId);
			if (share == null) {
				share = new Share(shareId, som.getName(), som);
				share.setId(shareId);
				shareRepo.save(share);
				share = shareRepo.findOne(shareId);
			}
			try {
				// Should be one year ago
				Calendar calendar = Util.getYearsFromNow(propService.getInt(PropertyConstants.YEARS_TO_COLLECT_HISTORY));
				
				HistQuotesQuery2V8Request request = new HistQuotesQuery2V8Request(share.getId(), calendar, Calendar.getInstance(), QueryInterval.DAILY);
				
				List<HistoricalQuote> quotes = request.getResult();
				
				for (HistoricalQuote quote : quotes) {
					ShareDayRate dayRate = getDayRateHist(quote, share, new ShareDayRate());
					
					dayRateRepo.save(dayRate);
				}
				calculatorService.updateMovingAverages(shareId);
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
			som.setStatus(TradeConstants.SHARE_STATUS_IMPORTED);
			somRepo.save(som);
		}
	}
	
	public void storeCurrentRates() throws IOException {
		for (Share share : shareRepo.findAll()) {
			
			ShareDayRate dayRate = new ShareDayRate();
			if (share.getDayRates().size() > 0) {				
				ShareDayRate lastRate = share.getDayRates().iterator().next();
				if (Util.isToday(lastRate.getActualDate())) {
					dayRate.setId(lastRate.getId());
					LOG.info("Is today: " + share.getId() + " rate id: " + lastRate.getId());
				} else {
					LOG.info("Is not today: " + share.getId());						
				}				
			}
			Calendar yesterday = Util.getDaysFromNow(1);
			Calendar tomorrow = Util.getTomorrow();
			HistQuotesQuery2V8Request request = new HistQuotesQuery2V8Request(share.getId(), yesterday, tomorrow, QueryInterval.DAILY);
			List<HistoricalQuote> quotes = request.getResult();
			
			if (!quotes.isEmpty()) {
				HistoricalQuote quoteToday = quotes.get(quotes.size() - 1);
				if (Util.isToday(quoteToday.getDate().getTime())) {
					dayRate = getDayRateHist(quoteToday, share, dayRate);
					try {
						dayRate = calculatorService.setMovingAverages(dayRate, share);
					} catch (ParseException e) {
						LOG.error("Parse exception when update moving averages, go on...");
					}
					dayRateRepo.save(dayRate);
				}
			}
			
		}
	}

	private ShareDayRate getDayRate(StockQuote quote, Share share, ShareDayRate dayRate) {
		dayRate.setEmptyValues();
		dayRate.setShare(share);
		try {
			dayRate.setActualDate(new Date());
			dayRate.setBuyRate(quote.getBid().doubleValue());
			dayRate.setMaxRate(quote.getDayHigh().doubleValue());
			dayRate.setMinRate(quote.getDayLow().doubleValue());
			dayRate.setSellRate(quote.getAsk().doubleValue());
			dayRate.setTradedVolume(quote.getVolume());
			dayRate = calculatorService.setMovingAverages(dayRate, share);
		} catch (Exception e) {
			LOG.debug("Failed to parse Quote, returning empty record");			
		}
		return dayRate;
	}

	
	private ShareDayRate getDayRateHist(HistoricalQuote quote, Share share, ShareDayRate dayRate) {
		dayRate.setEmptyValues();
		dayRate.setShare(share);
		try {
			dayRate.setActualDate(quote.getDate().getTime());
			dayRate.setBuyRate(quote.getClose().doubleValue());
			dayRate.setMaxRate(quote.getHigh().doubleValue());
			dayRate.setMinRate(quote.getLow().doubleValue());
			dayRate.setSellRate(quote.getClose().doubleValue());
			dayRate.setTradedVolume(quote.getVolume());
		} catch (Exception e) {
			LOG.debug("Failed to parse HistoricalQuote, returning empty record");			
		}
		return dayRate;
	}

	@Autowired
	public void setPropService(PropertyService propService) {
		this.propService = propService;
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
	public void setCalculatorService(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}

}
