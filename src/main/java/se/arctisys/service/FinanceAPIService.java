package se.arctisys.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arctisys.constants.PropertyConstants;
import se.arctisys.constants.TradeConstants;
import se.arctisys.domain.ErrorRecord;
import se.arctisys.domain.Share;
import se.arctisys.domain.ShareDayRate;
import se.arctisys.domain.ShareOnMarket;
import se.arctisys.repository.ErrorRepository;
import se.arctisys.repository.ShareDayRateRepository;
import se.arctisys.repository.ShareOnMarketRepository;
import se.arctisys.repository.ShareRepository;
import se.arctisys.util.Util;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

/**
 * Created by Björn Törnqvist, ArctiSys AB, 2016-02
 */
@Service
public class FinanceAPIService {

	private static final Logger LOG = LoggerFactory.getLogger(FinanceAPIService.class);

	private ErrorRepository errorRepo;
	private PropertyService propService;
	private ShareRepository shareRepo;
	private ShareDayRateRepository dayRateRepo;
	@Autowired
	private ShareOnMarketRepository somRepo;
	private CalculatorService calculatorService;


	public void readHistory() throws ParseException, IOException {
		LOG.info("Looking history!");
		List<ShareOnMarket> sharesToImport = somRepo.findSOMByStatus(TradeConstants.SHARE_STATUS_START_IMPORT);
		for (ShareOnMarket som : sharesToImport) {
			String shareId =  som.getId();
			LOG.info("Share Id : " + shareId);
			Share share = shareRepo.findOne(shareId);
			if (share == null) {
				share = new Share(shareId, som.getDescription(), som);
				share.setId(shareId);
				shareRepo.save(share);
				share = shareRepo.findOne(shareId);
			}
			try {
				// Should be one year ago
				Calendar calendar = Util.getYearsFromNow(propService.getInt(PropertyConstants.YEARS_TO_COLLECT_HISTORY));
				Stock stockYahoo = YahooFinance.get(share.getId(), calendar, Interval.DAILY);
				
				List<HistoricalQuote> quotes = stockYahoo.getHistory();
				
				for (HistoricalQuote quote : quotes) {
					ShareDayRate dayRate = getDayRate(quote, share);
					
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
	private ShareDayRate getDayRate(HistoricalQuote quote, Share share) {
		ShareDayRate dayRate = new ShareDayRate();
		dayRate.setEmptyValues();
		dayRate.setShare(share);
		dayRate.setCreationDate(new Date());		
		try {
			dayRate.setActualDate(quote.getDate().getTime());
			dayRate.setBuyRate(quote.getClose().doubleValue());
			dayRate.setMaxRate(quote.getHigh().doubleValue());
			dayRate.setMinRate(quote.getLow().doubleValue());
			dayRate.setSellRate(quote.getClose().doubleValue());
		} catch (Exception e) {
			LOG.debug("Failed to parse HistoricalQuote, returning empty record");			
		}
		return dayRate;
	}

	@Autowired
	public void setErrorRepo(ErrorRepository errorRepo) {
		this.errorRepo = errorRepo;
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
