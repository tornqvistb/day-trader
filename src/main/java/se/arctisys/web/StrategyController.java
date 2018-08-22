package se.arctisys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import se.arctisys.repository.StrategyRepository;

@Controller
public class StrategyController {
	
	@Autowired
	private StrategyRepository strategyRepo;

	@GetMapping("/admin/strategies")
	public String strategies(ModelMap model) {
		model.addAttribute("strategies", strategyRepo.findAll());
		return "admin/strategies";
	}

}
