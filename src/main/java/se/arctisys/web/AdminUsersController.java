package se.arctisys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import se.arctisys.repository.UserRepository;

@Controller
public class AdminUsersController {
	
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/admin/users")
	public String users(ModelMap model) {
		model.addAttribute("users", userRepo.findAll());
		return "admin/users";
	}
	
}
