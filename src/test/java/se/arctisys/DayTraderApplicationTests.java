package se.arctisys;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import se.arctisys.domain.Share;
import se.arctisys.model.Profit;
import se.arctisys.repository.ShareRepository;
import se.arctisys.service.CalculatorService;
import se.arctisys.util.Util;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DayTraderApplication.class)
@WebAppConfiguration
public class DayTraderApplicationTests {

	private static final Logger LOG = LoggerFactory.getLogger(DayTraderApplicationTests.class);
	
	@Autowired
	CalculatorService calcService;
	@Autowired
	ShareRepository shareRepo;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void calculateProfits() throws ParseException {
		Date startDate = Util.stringToDateAndTime("2015-04-11 00:00");
		Date endDate = Util.stringToDateAndTime("2016-03-09 23:59");
		Integer amount = 10000;
		List<Share> shares = shareRepo.findAll();
		Double totalResult = 0.0;
		LOG.info("Startdate: " + Util.dateAndTimeToString(startDate));
		LOG.info("Enddate: " + Util.dateAndTimeToString(endDate));
		for (Share share : shares){
			Profit profit = calcService.getProfitBetweenDates(startDate, endDate, share, amount);
			LOG.info("Share: " + share.getId());
			
			LOG.info("Total buy: " + profit.getTotalBuy());
			LOG.info("Total sell: " + profit.getTotalSell());
			LOG.info("Result: " + profit.getResult());
			LOG.info("Result (%): " + Util.round(100 * profit.getResult()/profit.getTotalBuy(), 2));
			/*
			LOG.info("Transactions:");
			for (Transaction trans : profit.getTransactions()) {
				LOG.info("- " + trans.getType() + ": " + trans.getAmount());
			}
			*/
			totalResult = totalResult + profit.getResult();
			LOG.info("---------------------------------------------");
		}
		LOG.info("Total result: " + totalResult);
	}
		
}
