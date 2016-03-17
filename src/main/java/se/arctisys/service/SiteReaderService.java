package se.arctisys.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arctisys.constants.PropertyConstants;
import se.arctisys.domain.ErrorRecord;
import se.arctisys.domain.Share;
import se.arctisys.domain.ShareDayRate;
import se.arctisys.repository.ErrorRepository;
import se.arctisys.repository.ShareDayRateRepository;
import se.arctisys.repository.ShareRepository;
import se.arctisys.util.Util;

/**
 * Created by Björn Törnqvist, ArctiSys AB, 2016-02
 */
@Service
public class SiteReaderService {

	private static final String GENERAL_FILE_ERROR = "Fel vid inläsning från site. ";

	private static final Logger LOG = LoggerFactory.getLogger(SiteReaderService.class);

	private ErrorRepository errorRepo;
	private PropertyService propService;
	private ShareRepository shareRepo;
	private ShareDayRateRepository dayRateRepo;
	private CalculatorService calculatorService;

	public void storeSiteValues() throws ParseException {
		LOG.info("Going to read from site");
		try {
			List<String> sites = new ArrayList<String>();
			
			sites.add("http://www.di.se/borssidor/small-cap/");
			sites.add("http://www.di.se/borssidor/mid-cap/");
			sites.add("http://www.di.se/borssidor/large-cap/");
			sites.add("http://www.di.se/borssidor/first-north/");
			for (String site : sites) {
				Document doc = Jsoup.connect(site).get();
				Element table = doc.select("table.fh-table-strip").get(0); //select the table.
				for (Element row : table.select("tr")) {
					Elements tds = row.select("td");
					if (tds.size() > 2) {
						ShareDayRate dayRate = getDayRateFromRow(tds);
						String shareId = tds.get(0).text();
						storeShareDayRates(dayRate, shareId);
					}
				}
			}
		} catch (IOException e) {
			saveError(GENERAL_FILE_ERROR + "IOException");
		}
	}

	private ShareDayRate getDayRateFromRow(Elements tds) throws ParseException {
		ShareDayRate rate = new ShareDayRate();
		rate.setActualDate(new Date());
		rate.setBuyRate(Util.stringToDouble(tds.get(4).text()));
		rate.setSellRate(Util.stringToDouble(tds.get(5).text()));
		rate.setMaxRate(Util.stringToDouble(tds.get(6).text()));
		rate.setMinRate(Util.stringToDouble(tds.get(7).text()));
		rate.setATH(Util.stringToDouble(tds.get(9).text()));
		rate.setChangeThisYearPerc(Util.stringToDouble(tds.get(8).text()));
		rate.setCreationDate(new Date());		
		return rate;
	}
	
	private void storeShareDayRates(ShareDayRate dayRate, String shareId) throws ParseException {
		// Get share from Share, share's dayrates are orderer by day desc
		Share share = shareRepo.findOne(shareId);
		if (share != null){
			if (share.getDayRates().size() > 0) {
				for (ShareDayRate rate : share.getDayRates()) {
					System.out.println("Actual date: " + Util.dateToString(rate.getActualDate()));
					if (Util.isToday(rate.getActualDate())) {
						dayRate.setId(rate.getId());
						System.out.println("Is today");
					} else {
						System.out.println("Is not today");						
					}
					break;
				}				
			}
			dayRate.setShare(share);
			dayRate.setMovingAverageShort(calculatorService.getFloatingAvarage(share, Util.getDateByDaysBack(propService.getLong(PropertyConstants.SHORT_MOVING_AVERAGE), dayRate.getActualDate()), dayRate.getActualDate()));
			dayRate.setMovingAverageMedium(calculatorService.getFloatingAvarage(share, Util.getDateByDaysBack(propService.getLong(PropertyConstants.MEDIUM_MOVING_AVERAGE), dayRate.getActualDate()), dayRate.getActualDate()));
			dayRate.setMovingAverageLong(calculatorService.getFloatingAvarage(share, Util.getDateByDaysBack(propService.getLong(PropertyConstants.LONG_MOVING_AVERAGE), dayRate.getActualDate()), dayRate.getActualDate()));
			dayRate.setLowFrequencyRate(calculatorService.getLowFrequence(share, Util.getDateByDaysBack(share.getDefaultFrequency(), dayRate.getActualDate()), dayRate.getActualDate()));
			dayRate.setHighFrequencyRate(calculatorService.getHighFrequence(share, Util.getDateByDaysBack(share.getDefaultFrequency(), dayRate.getActualDate()), dayRate.getActualDate()));
			// calculate new values
			dayRateRepo.save(dayRate);
		}		
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
