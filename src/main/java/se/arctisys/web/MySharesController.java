package se.arctisys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import se.arctisys.domain.User;
import se.arctisys.service.UserService;

@Controller
public class MySharesController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/myshares")
	public String showMyStocks(ModelMap model) {	
		User user = userService.getCurrentUser();
		model.addAttribute("userShares", user.getUserShares());
		System.out.println("Length shares: " + user.getUserShares().size());
		return "userMyShares";
	}
	@GetMapping("/user/addshare")
	public String addStock(ModelMap model) {		
		return "userAddShare";
	}
	@GetMapping("/user/transactions")
	public String showLoginForm(ModelMap model) {		
		return "userTransactions";
	}
	
}
