package se.arctisys.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("/admin/start")
	public String showAdminMenu(ModelMap model) {		
		return "admin/start";
	}

}
