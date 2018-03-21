package se.arctisys.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyStocksController {
	
	@GetMapping("/user/mystocks")
	public String showMyStocks(ModelMap model) {		
		return "userMyStocks";
	}
	@GetMapping("/user/addstock")
	public String addStock(ModelMap model) {		
		return "userAddStock";
	}
	@GetMapping("/user/transactions")
	public String showLoginForm(ModelMap model) {		
		return "userTransactions";
	}
	
}
