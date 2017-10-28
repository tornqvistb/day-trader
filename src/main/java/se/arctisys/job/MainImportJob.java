package se.arctisys.job;

import java.io.IOException;
import java.text.ParseException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import se.arctisys.service.AdvisorService;
import se.arctisys.service.FileImportService;
import se.arctisys.service.MailSenderService;
import se.arctisys.service.SiteReaderService;

/**
 * Created by Björn Törnqvist, ArctiSys AB, 2016-02
 */
public class MainImportJob implements Job {
    @Autowired
    private FileImportService importService;
    @Autowired
    private SiteReaderService readerService;
    @Autowired
    private AdvisorService advisorService;
    @Autowired
    private MailSenderService mailService;
    

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
    	try {
			readerService.storeAllStocksOnMarket();
			importService.readHistory();
			readerService.storeDaylyValues();			
			advisorService.checkForSignals();
			mailService.checkMailsToSend();
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
    }
}
