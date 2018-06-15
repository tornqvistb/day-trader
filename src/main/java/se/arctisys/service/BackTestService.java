package se.arctisys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arctisys.model.BackTestInput;
import se.arctisys.model.BackTestResult;
import se.arctisys.repository.ShareDayRateRepository;
import se.arctisys.repository.StrategyRepository;

@Service
public class BackTestService {

	@Autowired
	private StrategyService strategyService;
	@Autowired
	private StrategyRepository strategyRepo;
	@Autowired
	private ShareDayRateRepository dayRateRepo;
	
	private static final Logger LOG = LoggerFactory.getLogger(CalculatorService.class);
	
	public BackTestResult performBackTest(BackTestInput input) {
		BackTestResult result = new BackTestResult();
		//TODO
		/**
		 * Get list of day rates from dayRateRepo
		 * Loop through the day rates and check if it's time to sell / time to buy
		 */
		strategyService.setStrategy(strategyRepo.getOne(input.getStrategyId()));
		
		result.setStartValue(2000.0);
		result.setEndValue(3000.0);
		return result;
	}
}