package se.arctisys.service;

import static se.arctisys.constants.TradeConstants.JOB_STATUS_DONE;
import static se.arctisys.constants.TradeConstants.JOB_STATUS_START;
import static se.arctisys.constants.TradeConstants.TRANSACTION_TYPE_BUY;
import static se.arctisys.constants.TradeConstants.TRANSACTION_TYPE_SELL;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arctisys.domain.BackTestInput;
import se.arctisys.domain.BackTestJob;
import se.arctisys.domain.BackTestResult;
import se.arctisys.domain.ShareDayRate;
import se.arctisys.domain.Strategy;
import se.arctisys.domain.Transaction;
import se.arctisys.model.BackTestHolding;
import se.arctisys.repository.BackTestJobRepository;
import se.arctisys.repository.ShareDayRateRepository;
import se.arctisys.repository.StrategyRepository;

@Transactional
@Service
public class BackTestService {	

	@Autowired
	private StrategyService strategyService;
	@Autowired
	private StrategyRepository strategyRepo;
	@Autowired
	private ShareDayRateRepository dayRateRepo;	
	@Autowired
	private BackTestJobRepository jobRepo;	
		
	private static final Logger LOG = LoggerFactory.getLogger(CalculatorService.class);

	private BackTestHolding holding;
	private BackTestResult result;
	private BackTestInput input;
	
	public BackTestResult performBackTest(BackTestInput inputPar) {
		this.input = inputPar;
		result = new BackTestResult();
		holding = new BackTestHolding(); 
		holding.setAvailable(Double.valueOf(input.getAmount()));
		Strategy strategy = strategyRepo.getOne(input.getStrategy().getId());
		if (strategy != null) {
			LOG.info("strategy is not null");
		} else {
			LOG.info("strategy is null");
		}
		strategyService.setStrategy(strategy);
		
		List<ShareDayRate> dayRates = dayRateRepo.getDayRatesBetweenTwoDatesForShare(input.getStartDate(), input.getEndDate(), input.getShare().getId());
		result.setStartValue(Double.valueOf(input.getAmount()));
		String lastTransaction = TRANSACTION_TYPE_SELL;
		Double lastSellRate = 0.0;
		for (ShareDayRate dayRate : dayRates) {
			if (dayRate.getBuyRate() > 0.0 && dayRate.getSellRate() > 0.0) {
				LOG.debug("Backtest date: " + dayRate.getActualDateDisplay());
				LOG.debug("Backtest buyRate: " + dayRate.getBuyRate());
				LOG.debug("Backtest sellRate: " + dayRate.getSellRate());
				strategyService.setDayRate(dayRate);
				strategyService.setStrategy(strategyRepo.getOne(input.getStrategy().getId()));
				if (TRANSACTION_TYPE_SELL.equals(lastTransaction)) {
					if (strategyService.timeToBuy()) {
						LOG.info("Time to buy: " + dayRate.getActualDateDisplay() + ", rate: " + dayRate.getBuyRate());
						doBuy(dayRate);
						lastTransaction = TRANSACTION_TYPE_BUY;
					}
				} else {
					if (strategyService.timeToSell()) {
						LOG.info("Time to sell: " + dayRate.getActualDateDisplay() + ", rate: " + dayRate.getSellRate());
						doSell(dayRate);
						lastTransaction = TRANSACTION_TYPE_SELL;					
					}				
				}
				lastSellRate = dayRate.getSellRate();
			}
		}		
		
		result.setEndValueAvailable(holding.getAvailable());
		result.setEndValueInStocks(holding.getNoOfShares() * lastSellRate);
		result.setInput(inputPar);
		return result;
	}

	private void doBuy(ShareDayRate dayRate) {
		Transaction trans = new Transaction();
		trans.setType(TRANSACTION_TYPE_BUY);
		Long noOfShares = (long) Math.floor(holding.getAvailable() / dayRate.getBuyRate());
		LOG.debug("doBuy, noOfShares: " + noOfShares);
		trans.setNoOfShares(noOfShares);
		trans.setTransactionDate(dayRate.getActualDate());
		Double amount = noOfShares * dayRate.getBuyRate();
		LOG.debug("doBuy, amount: " + amount);
		trans.setAmount(amount);
		trans.setRate(dayRate.getBuyRate());
		holding.setAvailable(holding.getAvailable() - amount);
		holding.setLastBuyRate(dayRate.getBuyRate());
		holding.setNoOfShares(noOfShares);
		result.getTransactions().add(trans);
	}

	private void doSell(ShareDayRate dayRate) {
		Transaction trans = new Transaction();
		trans.setType(TRANSACTION_TYPE_SELL);
		trans.setNoOfShares(holding.getNoOfShares());
		LOG.debug("doSell, noOfShares: " + holding.getNoOfShares());
		trans.setTransactionDate(dayRate.getActualDate());
		Double amount = holding.getNoOfShares() * dayRate.getSellRate();
		LOG.debug("doSell, amount: " + amount);
		trans.setAmount(amount);
		trans.setRate(dayRate.getSellRate());
		holding.setAvailable(holding.getAvailable() + amount);
		holding.setNoOfShares(0L);
		result.getTransactions().add(trans);
	}

	public void runBackTestJobs() {
		List<BackTestJob> jobs = jobRepo.findByStatus(JOB_STATUS_START);
		for (BackTestJob job : jobs) {
			LOG.info("Running job " + job.getName());
			for (BackTestInput input : job.getInputList()) {
				LOG.info("job entity " + input.getShare().getId());
				BackTestResult result = performBackTest(input);
				input.setBackTestResult(result);
				LOG.info("Back test " + input.getShare().getId() + " done with end value " + result.getEndValue());
			}
			job.setExecutionDate(new Date());
			job.setStatus(JOB_STATUS_DONE);
			jobRepo.save(job);
		}
		
	}
	
}