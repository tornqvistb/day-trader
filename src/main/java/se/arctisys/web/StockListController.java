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
public class StockListController {
	
	@Autowired
	private ShareRepository shareRepo;
			
	@RequestMapping("/shares")
	public String showOrderList(ModelMap model) {
		model.addAttribute("reqAttr", new RequestAttributes(0));
		List<Share> shares = shareRepo.findAll();
		model.addAttribute("shares", shares);
		return "shares";
	}
	
}
