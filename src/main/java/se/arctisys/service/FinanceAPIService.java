package se.arctisys.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import se.arctisys.yahoofinance.QueryInterval;
import se.arctisys.yahoofinance.Quote;
import se.arctisys.yahoofinance.QuotesRequest;

/**
 * Created by Björn Törnqvist, ArctiSys AB, 2016-02
 */
@Service
public class FinanceAPIService {

	private static final Logger LOG = LoggerFactory.getLogger(FinanceAPIService.class);

	@Autowired
	private PropertyService propService;
	@Autowired
	private ShareRepository shareRepo;
	@Autowired
	private ShareDayRateRepository dayRateRepo;
	@Autowired
	private ShareOnMarketRepository somRepo;
	@Autowired
	private CalculatorService calculatorService;


	public void readHistory() throws ParseException, IOException {
		LOG.info("Looking for history!");
		List<ShareOnMarket> sharesToImport = somRepo.findSOMByStatus(TradeConstants.SHARE_STATUS_START_IMPORT);
		for (ShareOnMarket som : sharesToImport) {
			String shareId =  som.getId();
			LOG.info("Share Id : " + shareId);
			Share share = shareRepo.findOne(shareId);
			if (share == null) {
				share = new Share(shareId, som.getName(), som, TradeConstants.SHARE_STATUS_NEW);
				shareRepo.save(share);
				share = shareRepo.findOne(shareId);
			}
			try {
				// Should be one year ago
				Calendar calendar = Util.getYearsFromNow(propService.getInt(PropertyConstants.YEARS_TO_COLLECT_HISTORY));
				
				QuotesRequest request = new QuotesRequest(share.getId(), calendar, Calendar.getInstance(), QueryInterval.DAILY, propService.getString(PropertyConstants.QUOTES_BASE_URL));
				
				List<Quote> quotes = request.getResult();
				
				for (Quote quote : quotes) {
					ShareDayRate dayRate = getDayRate(quote, share, new ShareDayRate());
					
					dayRateRepo.save(dayRate);
				}
				calculatorService.updateMovingAverages(shareId);
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
			som.setStatus(TradeConstants.SHARE_STATUS_IMPORTED);
			somRepo.save(som);
			share.setStatus(TradeConstants.SHARE_STATUS_IMPORTED);
			shareRepo.save(share);
		}
	}
	
	public void storeCurrentRates() throws IOException {
		for (Share share : shareRepo.findByStatus(TradeConstants.SHARE_STATUS_IMPORTED)) {
			
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
			Calendar startDay = Util.getDaysFromNow(3);
			Calendar tomorrow = Util.getTomorrow();
			QuotesRequest request = new QuotesRequest(share.getId(), startDay, tomorrow, QueryInterval.DAILY,propService.getString(PropertyConstants.QUOTES_BASE_URL));
			List<Quote> quotes = request.getResult();
			
			if (!quotes.isEmpty()) {
				Quote quoteToday = quotes.get(quotes.size() - 1);
				if (Util.isToday(quoteToday.getDate().getTime())) {
					dayRate = getDayRate(quoteToday, share, dayRate);
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
	
	private ShareDayRate getDayRate(Quote quote, Share share, ShareDayRate dayRate) {
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

}
