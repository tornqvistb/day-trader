package se.arctisys.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.arctisys.domain.ShareDayRate;
import se.arctisys.domain.Strategy;

public class StrategyServiceTest {

	private StrategyService service;
	
	private Strategy shortStrategy;
	private Strategy mediumStrategy;
	private Strategy longStrategy;
	
	private ShareDayRate rate1;
	
	@Before
	public void setUp() throws Exception {
		service = new StrategyService();
		shortStrategy = new Strategy();
		shortStrategy.setCompareLongToMedium(false);
		shortStrategy.setCompareMediumToShort(false);
		shortStrategy.setCompareShortToCurrent(true);
		shortStrategy.setMultipleShortToCurrent(1.01);		
		shortStrategy.setMultipleMediumToShort(1.01);
		shortStrategy.setMultipleLongToMedium(1.01);

		mediumStrategy = new Strategy();
		mediumStrategy.setCompareLongToMedium(false);
		mediumStrategy.setCompareMediumToShort(true);
		mediumStrategy.setCompareShortToCurrent(true);
		mediumStrategy.setMultipleShortToCurrent(1.01);		
		mediumStrategy.setMultipleMediumToShort(1.01);
		mediumStrategy.setMultipleLongToMedium(1.01);

		longStrategy = new Strategy();
		longStrategy.setCompareLongToMedium(true);
		longStrategy.setCompareMediumToShort(true);
		longStrategy.setCompareShortToCurrent(true);
		longStrategy.setMultipleShortToCurrent(1.01);		
		longStrategy.setMultipleMediumToShort(1.01);
		longStrategy.setMultipleLongToMedium(1.01);

		rate1 = new ShareDayRate();
		rate1.setEmptyValues();
		rate1.setMovingAverageShort(100.0);		
		rate1.setMovingAverageMedium(100.0);
		rate1.setMovingAverageLong(100.0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assert (service != null);
		
		
	}

}
