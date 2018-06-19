package se.arctisys.web;

import java.text.ParseException;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import se.arctisys.model.BackTestInput;
import se.arctisys.model.BackTestResult;
import se.arctisys.repository.ShareRepository;
import se.arctisys.repository.StrategyRepository;
import se.arctisys.service.BackTestService;
import se.arctisys.util.Util;

@Controller
public class BackTestController {

	@Autowired
	private BackTestService backTestService;
	@Autowired
	private StrategyRepository strategyRepo;
	@Autowired
	private ShareRepository shareRepo;
	
	@GetMapping("/user/backtest")
	public String showBackTest(ModelMap model) {
		try {

			BackTestInput input = new BackTestInput();
			input.setStartDate(Util.getDateByDaysBack(365L));
			input.setEndDate(new Date());
			model.addAttribute("backTestInput", input);
			model.addAttribute("backTestResult", null);
			fillLists(model);

		} catch (ParseException e) {
		}
		return "userBackTest";
	}

	private void fillLists (ModelMap model) {
		model.addAttribute("allShares", shareRepo.findAll());
		model.addAttribute("allStrategies", strategyRepo.findAll());				
	}
	
	@PostMapping("/user/backtest")
	public String runBackTest(ModelMap model, @Valid @ModelAttribute BackTestInput input, BindingResult bindingResult) {
		System.out.println("Posted backtest");
		if (bindingResult.hasErrors()) {
			String errorText = "";
			for (ObjectError error : bindingResult.getAllErrors()) {
				errorText = errorText + error.getDefaultMessage();
			}						
		} else {
			BackTestResult result = backTestService.performBackTest(input);
			model.addAttribute("backTestResult", result);
		}
		model.addAttribute("backTestInput", input);
		fillLists(model);
		return "userBackTest";
	}
	
}
