package se.arctisys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import se.arctisys.domain.User;
import se.arctisys.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	
	public User getCurrentUser() {
		User user = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName(); //get logged in username	    
	    List<User> users = userRepo.findUsersByEmail(email);
	    if (!users.isEmpty()) {
	    	user = users.get(0);
	    }
	    return user;

	}
	
}
