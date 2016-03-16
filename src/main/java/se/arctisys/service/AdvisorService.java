package se.arctisys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arctisys.repository.PropertyRepository;

@Service
public class AdvisorService {

	@Autowired
	private PropertyRepository propertyRepo;
	
	public void checkForSignals() {
		
	}

	
}