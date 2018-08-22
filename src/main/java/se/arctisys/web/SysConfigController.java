package se.arctisys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import se.arctisys.domain.SystemProperty;
import se.arctisys.model.RequestAttributes;
import se.arctisys.repository.PropertyRepository;

@Controller
public class SysConfigController {
	
	@Autowired
	private PropertyRepository propertyRepo;

	@GetMapping("/admin/sysconfig")
	public String sysconfig(ModelMap model) {
		List<SystemProperty> properties = propertyRepo.findAll();
		model.put("properties", properties);
		RequestAttributes reqAttr = loadReqAttr(properties);
		model.put("reqAttr", reqAttr);
		return "admin/sysconfig";
	}
	/*
	@PostMapping(value = "/admin/sysconfig/save")
	public String saveSettings(@ModelAttribute List<SystemProperty> properties,
			ModelMap model) {
		for (SystemProperty prop : properties) {
			SystemProperty theProp = propertyRepo.getOne(prop.getId());
			theProp.setNumberValue(prop.getNumberValue());
			theProp.setStringValue(prop.getStringValue());
			propertyRepo.save(theProp);
		}
		model.addAttribute("properties", propertyRepo.findAll());
		return "admin/sysconfig";
	}
	*/
	@PostMapping(value = "/admin/sysconfig/save")
	public String saveSettings(@ModelAttribute RequestAttributes reqAttr,
			ModelMap model) {
		for (SystemProperty prop : reqAttr.getSystemProperties()) {
			SystemProperty theProp = propertyRepo.getOne(prop.getId());
			theProp.setNumberValue(prop.getNumberValue());
			theProp.setStringValue(prop.getStringValue());
			propertyRepo.save(theProp);
		}
		List<SystemProperty> properties = propertyRepo.findAll();
		model.put("properties", properties);
		reqAttr = loadReqAttr(properties);
		reqAttr.setThanksMessage("Uppdatering genomförd");
		model.put("reqAttr", reqAttr);
		return "admin/sysconfig";
	}
	
	private RequestAttributes loadReqAttr(List<SystemProperty> properties) {
		RequestAttributes reqAttr = new RequestAttributes();
		reqAttr.setSystemProperties(properties);
		return reqAttr;
	}

		
}
