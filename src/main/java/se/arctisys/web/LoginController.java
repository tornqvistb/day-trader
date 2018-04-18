package se.arctisys.web;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import scala.annotation.meta.setter;
import se.arctisys.domain.Account;
import se.arctisys.domain.Role;
import se.arctisys.domain.User;
import se.arctisys.repository.AccountRepository;
import se.arctisys.repository.RoleRepository;
import se.arctisys.repository.UserRepository;



@Controller
public class LoginController {

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AccountRepository accountRepo;

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
	@GetMapping("/register")
	public String renderRegisterPage(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		System.out.println("Rendering register page");
		return "register";
	}
	@PostMapping("/register")
	public String registerUser(ModelMap model, @Valid @ModelAttribute User user, BindingResult bindingResult) {
		System.out.println("In registerUser");
		if (bindingResult.hasErrors()) {
			return "register";
		}
		if (!userRepo.findUsersByEmail(user.getEmail()).isEmpty()) {
			model.addAttribute("error", "Epostkonto " + user.getEmail() + " finns redan registrerat på en användare");
			return "register";
		}
		user.setActive(1);
		user.setUsername(user.getEmail());
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleRepo.findOne(1));
		user.setRoles(roles);
		user = userRepo.save(user);
		Account account = new Account();
		account.setUser(user);
		accountRepo.save(account);
		System.out.println("Saved user");
		model.addAttribute("success", "Användare " + user.getName() + " skapad");
		model.addAttribute("user", new User());
		
		return "register";
	}
	
}
