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
public class FileImportService {

	private static final String GENERAL_FILE_ERROR = "Fel vid inläsning av fil. ";

	private static final Logger LOG = LoggerFactory.getLogger(FileImportService.class);

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
				Calendar calendar = new GregorianCalendar(2017,0,1);
				Stock stockYahoo = YahooFinance.get(share.getId(), calendar, Interval.DAILY);
				
				for (HistoricalQuote quote : stockYahoo.getHistory()) {
					ShareDayRate dayRate = getDayRate(quote, share);
					dayRateRepo.save(dayRate);
				}
/*				
				String url = getFileImportUrl(shareId);
				InputStream input = new URL(url).openStream();

				BufferedReader in = new BufferedReader(new InputStreamReader(input));

				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					ShareDayRate dayRate = getDayRate(inputLine, share);
					if (dayRate != null) {
						dayRateRepo.save(dayRate);
					} else {
						saveError(GENERAL_FILE_ERROR + inputLine);
					}
				}
				in.close();
				*/
				// Commented for now 20171028 calculatorService.updateAverageAndFrequence(shareId, share.getDefaultFrequency());
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
			som.setStatus(TradeConstants.SHARE_STATUS_IMPORTED);
			somRepo.save(som);
		}
	}



	private String getFileImportUrl(String shareId) {
		String url = propService.getString(PropertyConstants.FILE_IMPORT_URL);
		url = url.replace("#ID", shareId);
		
		LocalDate yesterday = LocalDate.now().minusDays(1);
		
		String yahooMonth = String.valueOf(yesterday.getMonth().getValue() - 1);
		if (yahooMonth.length() == 1) {
			yahooMonth = "0" + yahooMonth;
		}
		String year = String.valueOf(yesterday.getYear());
		String lastYear = String.valueOf(yesterday.getYear() - 1);
		String day = String.valueOf(yesterday.getDayOfMonth());

		url = url.replace("#FR_MON", yahooMonth);
		url = url.replace("#FR_DAY", day);
		url = url.replace("#FR_YEAR", lastYear);
		url = url.replace("#TO_MON", yahooMonth);
		url = url.replace("#TO_DAY", day);
		url = url.replace("#TO_YEAR", year);
				
		return url;
	}
	
	private ShareDayRate getDayRate(HistoricalQuote quote, Share share) {
		ShareDayRate dayRate = new ShareDayRate();
		dayRate.setEmptyValues();
		dayRate.setShare(share);
		dayRate.setCreationDate(new Date());
		dayRate.setActualDate(quote.getDate().getTime());
		dayRate.setBuyRate(quote.getClose().doubleValue());
		dayRate.setMaxRate(quote.getHigh().doubleValue());
		dayRate.setMinRate(quote.getLow().doubleValue());
		dayRate.setSellRate(quote.getClose().doubleValue());
		return dayRate;
	}
	
	private ShareDayRate getDayRate(String row, Share share) {
		ShareDayRate dayRate = null;
		try {
			if (row.startsWith("2")) {
				dayRate = new ShareDayRate();
				dayRate.setEmptyValues();
				String[] arr = row.split(",");
				if (!arr[0].isEmpty())
					dayRate.setActualDate(stringToDate(arr[0]));
				if (!arr[4].isEmpty())
					dayRate.setBuyRate(Util.stringToDouble(arr[4], Locale.US)); // Closing
																				// price
				dayRate.setCreationDate(new Date()); // Closing price
				if (!arr[2].isEmpty())
					dayRate.setMaxRate(Util.stringToDouble(arr[2], Locale.US)); // High
																				// price
				if (!arr[3].isEmpty())
					dayRate.setMinRate(Util.stringToDouble(arr[3], Locale.US)); // Low
																				// price
				if (!arr[4].isEmpty())
					dayRate.setSellRate(Util.stringToDouble(arr[4], Locale.US)); // Closing
																					// price
				dayRate.setShare(share);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dayRate;
	}

	private Date stringToDate(String dateStr) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(dateStr);
		return date;
	}

	private void saveError(String errorText) {
		errorRepo.save(new ErrorRecord(errorText));
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
