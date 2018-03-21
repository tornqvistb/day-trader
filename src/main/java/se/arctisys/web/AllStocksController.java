package se.arctisys.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AllStocksController {
	
	@GetMapping("/user/allstocks")
	public String showAllStocks(ModelMap model) {		
		return "userAllStocks";
	}
	
}
