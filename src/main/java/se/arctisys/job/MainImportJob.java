package se.arctisys.job;

import java.io.IOException;
import java.text.ParseException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import se.arctisys.service.MainImportService;
import se.arctisys.service.SiteReaderService;

/**
 * Created by Björn Törnqvist, ArctiSys AB, 2016-02
 */
public class MainImportJob implements Job {
    @Autowired
    private MainImportService importService;
    @Autowired
    private SiteReaderService readerService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
    	try {
			importService.moveFiles();
			readerService.storeSiteValues();
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
    }
}
