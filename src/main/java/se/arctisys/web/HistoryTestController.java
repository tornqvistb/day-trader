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
import se.arctisys.util.Util;

@Controller
public class HistoryTestController {
	
	@Autowired
	private StrategyRepository strategyRepo;
	@Autowired
	private ShareRepository shareRepo;
	
	@GetMapping("/user/historytest")
	public String showHistoryTest(ModelMap model) {
		try {

			BackTestInput backTestInput = new BackTestInput();
			backTestInput.setStartDate(Util.getDateByDaysBack(365L));
			backTestInput.setEndDate(new Date());
			model.addAttribute("backTestInput", backTestInput);
			model.addAttribute("backTestResult", null);
			fillLists(model);

		} catch (ParseException e) {
		}
		return "userHistoryTest";
	}

	private void fillLists (ModelMap model) {
		model.addAttribute("allShares", shareRepo.findAll());
		model.addAttribute("allStrategies", strategyRepo.findAll());				
	}
	
	@PostMapping("/user/historytest")
	public String runHistoryTest(ModelMap model, @Valid @ModelAttribute BackTestInput backTestInput, BindingResult bindingResult) {
		System.out.println("Posted historytest");
		if (bindingResult.hasErrors()) {
			String errorText = "";
			for (ObjectError error : bindingResult.getAllErrors()) {
				errorText = errorText + error.getDefaultMessage();
			}						
		} else {
			BackTestResult result = new BackTestResult();
			result.setStartValue(2000.0);
			result.setEndValue(3000.0);
			model.addAttribute("backTestResult", result);
		}
		model.addAttribute("backTestInput", backTestInput);
		fillLists(model);
		return "userHistoryTest";
	}
	
}
