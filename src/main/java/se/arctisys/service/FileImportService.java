package se.arctisys.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import se.arctisys.repository.ErrorRepository;
import se.arctisys.repository.ShareDayRateRepository;
import se.arctisys.repository.ShareRepository;

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
    private CalculatorService calculatorService;
    
	public void moveFiles() throws ParseException, IOException {
	    String fileSourceFolder = propService.getString(PropertyConstants.FILE_INCOMING_FOLDER);	    
	    String fileDestFolder = propService.getString(PropertyConstants.FILE_PROCESSED_FOLDER);	    
	    String fileErrorFolder = propService.getString(PropertyConstants.FILE_ERROR_FOLDER);	    
        LOG.info("Looking for files to move!");
		final File inputFolder = new File(fileSourceFolder);
		File[] filesInFolder = inputFolder.listFiles();
		if (filesInFolder != null) {
			for (final File fileEntry : filesInFolder) {
				LOG.info("FILE : " + fileEntry.getName());
				Path source = Paths.get(fileSourceFolder + "/" + fileEntry.getName());
				Path target = Paths.get(fileDestFolder + "/" + fileEntry.getName());
				Path errorTarget = Paths.get(fileErrorFolder + "/" + fileEntry.getName());
				String shareId = getShareIdFromFileName(fileEntry.getName());
				Share share = shareRepo.findOne(shareId);
				if (share == null) {
					
					
					
					share = new Share(shareId,
							TradeConstants.STRATEGY_LONG,
							shareId,
							TradeConstants.CURRENCY_SEK,
							propService.getLong(PropertyConstants.DEFAULT_BUY_AMOUNT),
							propService.getLong(PropertyConstants.DEFAULT_MAX_HOLDING_AMOUNT),
							propService.getLong(PropertyConstants.DEFAULT_RATE_FREQUENCY)
							);
					share.setId(shareId);
					shareRepo.save(share);
					share = shareRepo.findOne(shareId);
				}
				try {
					List<String> rows = Files.readAllLines(source);
					for (String row : rows) {
						ShareDayRate dayRate = getDayRate(row, share);
						if (dayRate != null) {
							dayRateRepo.save(dayRate);
						} else {
							saveError(GENERAL_FILE_ERROR + row);
						}
					}
					calculatorService.updateAverageAndFrequence(shareId, share.getFrequency());
					Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
					Files.move(source, errorTarget, StandardCopyOption.REPLACE_EXISTING);
					throw e;
				}
			}
		}
	}

	private String getShareIdFromFileName(String fileName) {
		Integer idx = fileName.indexOf('-');
		return fileName.substring(0, idx);
	}
	
    private ShareDayRate getDayRate(String row, Share share) {
    	ShareDayRate dayRate = null;
    	try {
			if (row.startsWith("2")) {
				dayRate = new ShareDayRate();
				dayRate.setEmptyValues();
				String[] arr = row.split(";");
				if (!arr[0].isEmpty()) dayRate.setActualDate(stringToDate(arr[0]));
				if (!arr[6].isEmpty()) dayRate.setBuyRate(stringToDouble(arr[6])); //Closing price
				dayRate.setCreationDate(new Date()); //Closing price
				if (!arr[4].isEmpty()) dayRate.setMaxRate(stringToDouble(arr[4])); // High price
				if (!arr[5].isEmpty()) dayRate.setMinRate(stringToDouble(arr[5])); // Low price
				if (!arr[6].isEmpty()) dayRate.setSellRate(stringToDouble(arr[6])); //Closing price
				dayRate.setShare(share);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return dayRate;
    }
	
    private Double stringToDouble(String value) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = format.parse(value);
        Double doubleValue = number.doubleValue();
        return doubleValue;
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
