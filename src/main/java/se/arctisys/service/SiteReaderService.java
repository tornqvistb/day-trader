package se.arctisys.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
import se.arctisys.constants.TradeConstants;
import se.arctisys.domain.ErrorRecord;
import se.arctisys.domain.Share;
import se.arctisys.domain.ShareOnMarket;
import se.arctisys.repository.ErrorRepository;
import se.arctisys.repository.ShareOnMarketRepository;
import se.arctisys.repository.ShareRepository;

/**
 * Created by Björn Törnqvist, ArctiSys AB, 2016-02
 */
@Service
public class SiteReaderService {

	private static final String GENERAL_FILE_ERROR = "Fel vid inläsning från site. ";

	private static final Logger LOG = LoggerFactory.getLogger(SiteReaderService.class);

	@Autowired
	private ErrorRepository errorRepo;
	@Autowired
	private PropertyService propService;
	@Autowired
	private ShareOnMarketRepository SOMRepo;
	@Autowired
	private ShareRepository shareRepo;
	
	public void storeAllStocksOnMarket() throws ParseException {
		LOG.info("Going to read from site");
		try {
			List<String> sites = new ArrayList<String>();
			
			sites.add(propService.getString(PropertyConstants.STOCK_SITE_URL));
			String stockStartSiteUrl = propService.getString(PropertyConstants.STOCK_SITE_URL_START);
			for (String site : sites) {
				Document doc = Jsoup.connect(site).get();
				Element table = doc.select("table#listedCompanies").get(0); //select the table.
				for (Element row : table.select("tr")) {
					Elements tds = row.select("td");
					storeShareOnMarket(tds, stockStartSiteUrl);
				}
			}
		} catch (IOException e) {
			LOG.error("Ett fel uppstod i storeAllStocksOnMarket: ", e);
			saveError(GENERAL_FILE_ERROR + "IOException");
		}
	}
	
	
	private String getShareIdFromSymbol(String symbol) {
		return symbol.replace(" ", "-") + ".ST";
	}


	
	private void storeShareOnMarket(Elements tds, String stockSiteStartUrl) {
		if (tds != null && tds.size() > 4) {
			ShareOnMarket som = new ShareOnMarket(
					getShareIdFromSymbol(tds.get(1).text()),
					tds.get(0).text(),
					tds.get(2).text(),
					tds.get(4).text(),
					stockSiteStartUrl + tds.get(0).select("a[href]").attr("href")
					);
			if (SOMRepo.findOne(som.getId()) == null) {
				SOMRepo.save(som);
				Share share = new Share(som.getId(), som.getName(), som, TradeConstants.SHARE_STATUS_NEW);
				shareRepo.save(share);
			}
		}
	}
		
	private void saveError(String errorText) {
		errorRepo.save(new ErrorRecord(errorText));
	}

}
