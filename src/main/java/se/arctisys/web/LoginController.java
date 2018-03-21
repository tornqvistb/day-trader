package se.arctisys.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	 @GetMapping("/login")
	public String showLoginForm(ModelMap model) {
		System.out.println("Hej");
		return "login";
	}
	@RequestMapping("/index")
	public String showStartPage(ModelMap model) {
		return "index";
	}
	
}
