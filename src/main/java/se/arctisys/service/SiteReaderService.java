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
import se.arctisys.domain.ErrorRecord;
import se.arctisys.domain.ShareOnMarket;
import se.arctisys.repository.ErrorRepository;
import se.arctisys.repository.ShareOnMarketRepository;

/**
 * Created by Björn Törnqvist, ArctiSys AB, 2016-02
 */
@Service
public class SiteReaderService {

	private static final String GENERAL_FILE_ERROR = "Fel vid inläsning från site. ";

	private static final Logger LOG = LoggerFactory.getLogger(SiteReaderService.class);

	private ErrorRepository errorRepo;
	private PropertyService propService;
	private ShareOnMarketRepository SOMRepo;
	
	public void storeAllStocksOnMarket() throws ParseException {
		LOG.info("Going to read from site");
		try {
			List<String> sites = new ArrayList<String>();
			
			sites.add(propService.getString(PropertyConstants.STOCK_SITE_URL));
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
	public void setShareOnMarketRepo(ShareOnMarketRepository SOMRepo) {
		this.SOMRepo = SOMRepo;
	}

}
