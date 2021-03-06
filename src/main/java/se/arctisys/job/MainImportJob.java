package se.arctisys.job;

import java.io.IOException;
import java.text.ParseException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import se.arctisys.service.AdvisorService;
import se.arctisys.service.BackTestService;
import se.arctisys.service.FinanceAPIService;
import se.arctisys.service.MailSenderService;
import se.arctisys.service.SiteReaderService;

/**
 * Created by Björn Törnqvist, ArctiSys AB, 2016-02
 */
public class MainImportJob implements Job {
    @Autowired
    private FinanceAPIService importService;
    @Autowired
    private SiteReaderService readerService;
    @Autowired
    private AdvisorService advisorService;
    @Autowired
    private MailSenderService mailService;
    @Autowired
    private BackTestService backTestService;
    

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
    	try {
			readerService.storeAllStocksOnMarket();
			importService.readHistory();
			importService.storeCurrentRates();
			advisorService.checkForSignals();
			mailService.checkMailsToSend();
			// execute orders with bank...
			backTestService.runBackTestJobs();
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
    }
}
