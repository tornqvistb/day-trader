package se.arctisys.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import se.arctisys.domain.ShareOnMarket;
import se.arctisys.repository.ErrorRepository;
import se.arctisys.repository.ShareDayRateRepository;
import se.arctisys.repository.ShareOnMarketRepository;
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
	private ShareOnMarketRepository SOMRepo;

	public void storeDaylyValues() throws ParseException {
		LOG.info("Going to read from site");
		try {
			List<String> sites = new ArrayList<String>();
			
			sites.add("http://bors.six.se/ttspektra-web/gpse/equities?entry=stockholm-omx-all");
			for (String site : sites) {
				Document doc = Jsoup.connect(site).get();
				Element table = doc.select("table.table-sortable").get(0); //select the table.
				for (Element row : table.select("tr")) {
					Elements tds = row.select("td");
					Share share = getShare(tds.first());
					if (share != null) {						
						ShareDayRate dayRate = getDayRateFromRow(tds);						
						storeShareDayRates(dayRate, share);
					}
				}
			}
		} catch (IOException e) {
			saveError(GENERAL_FILE_ERROR + "IOException");
		}
	}

	public void storeAllStocksOnMarket() throws ParseException {
		LOG.info("Going to read from site");
		try {
			List<String> sites = new ArrayList<String>();
			
			sites.add("http://bors.six.se/ttspektra-web/gpse/equities?entry=stockholm-omx-all");
			for (String site : sites) {
				Document doc = Jsoup.connect(site).get();
				Element table = doc.select("table.table-sortable").get(0); //select the table.
				for (Element row : table.select("tr")) {
					Elements tds = row.select("td");
					storeShareOnMarket(tds.first());
				}
			}
		} catch (IOException e) {
			saveError(GENERAL_FILE_ERROR + "IOException");
		}
	}
	
	
	private Share getShare(Element td) {
		Share share = null;
		if (td != null && td.select("a[href]").size() > 0) {
			Element link = td.select("a[href]").get(0);
			String shareId = getShareIdFromLink(link);
			String shareName = link.text();
			
			share = shareRepo.findOne(shareId);
			if (share != null) {
				share.setDescription(shareName);
				shareRepo.save(share);
			}
		}
		return share;
	}
	
	private String getShareIdFromLink(Element link) {
		String href = link.attr("href");
		int startId = href.indexOf("id=") + 3;
		int endId = href.indexOf("&entry");
		return href.substring(startId, endId).replace(".SE", ".ST");
	}
	
	private void storeShareOnMarket(Element td) {
		if (td != null && td.select("a[href]").size() > 0) {
			Element link = td.select("a[href]").get(0);
			ShareOnMarket som = new ShareOnMarket(getShareIdFromLink(link), link.text());
			if (SOMRepo.findOne(som.getId()) == null) {
				SOMRepo.save(som);
			}
		}
	}
	
	private ShareDayRate getDayRateFromRow(Elements tds) throws ParseException {
		ShareDayRate rate = new ShareDayRate();
		rate.setActualDate(new Date());
		rate.setBuyRate(Util.stringToDouble(tds.get(3).text(), Locale.FRANCE));
		rate.setSellRate(Util.stringToDouble(tds.get(4).text(), Locale.FRANCE));
		rate.setMaxRate(Util.stringToDouble(tds.get(6).text(), Locale.FRANCE));
		rate.setMinRate(Util.stringToDouble(tds.get(7).text(), Locale.FRANCE));
		//rate.setATH(Util.stringToDouble(tds.get(9).text()));
		//rate.setChangeThisYearPerc(Util.stringToDouble(tds.get(8).text()));
		rate.setTradedVolume(Util.stringToLong(tds.get(8).text()));
		rate.setCreationDate(new Date());		
		return rate;
	}
	
	private void storeShareDayRates(ShareDayRate dayRate, Share share) throws ParseException {
		// Get share from Share, share's dayrates are orderer by day desc
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
	@Autowired
	public void setShareOnMarketRepo(ShareOnMarketRepository SOMRepo) {
		this.SOMRepo = SOMRepo;
	}

}
