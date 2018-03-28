package se.arctisys.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import se.arctisys.domain.User;
import se.arctisys.repository.UserRepository;
import se.arctisys.service.UserService;

@Controller
public class AccountController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/user/account")
	public String showMyAccount(ModelMap model) {
    	model.addAttribute("user", userService.getCurrentUser());
		return "userAccount";
	}

	@PostMapping("/user/account")
	public String updateUser(ModelMap model, @Valid @ModelAttribute User user, BindingResult bindingResult) {
		userRepo.save(user);
		model.addAttribute("success", "Användare " + user.getName() + " uppdaterad");
		model.addAttribute("user", user);
		return "userAccount";
	}

	
}
