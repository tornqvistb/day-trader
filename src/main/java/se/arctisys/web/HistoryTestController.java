package se.arctisys.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HistoryTestController {
	
	@GetMapping("/user/historytest")
	public String showHistoryTest(ModelMap model) {		
		return "userHistoryTest";
	}
	
}
