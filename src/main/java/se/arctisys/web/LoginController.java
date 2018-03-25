package se.arctisys.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/")
	public String showStartPage() {
		return "redirect:home";
	}
	
	@GetMapping("/home")
	public String renderHomePage(ModelMap model) {
		System.out.println("Rendering home page");
		return "home";
	}
	@GetMapping("/login")
	public String showLoginForm(ModelMap model) {
		System.out.println("Rendering login page");
		return "login";
	}
	@GetMapping("/403")
	public String showUnauthorizedPage(ModelMap model) {
		return "403";
	}
	
}