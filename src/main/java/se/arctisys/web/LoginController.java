package se.arctisys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import se.arctisys.domain.Share;
import se.arctisys.model.RequestAttributes;
import se.arctisys.repository.ShareRepository;

@Controller
public class LoginController {
	
	@RequestMapping("login")
	public String showLoginForm(ModelMap model) {
		return "login";
	}
	@RequestMapping("index")
	public String showStartPage(ModelMap model) {
		return "index";
	}
	
}
