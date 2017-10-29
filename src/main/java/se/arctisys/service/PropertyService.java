package se.arctisys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arctisys.domain.SystemProperty;
import se.arctisys.repository.PropertyRepository;

@Service
public class PropertyService {

	@Autowired
	private PropertyRepository propertyRepo;
	
	public String getString(String propertyId) {
		String result = "";
		SystemProperty prop = propertyRepo.findOne(propertyId);
		if (prop != null) {
			result = prop.getStringValue();
		}
		return result;
	}
	public Long getLong(String propertyId) {
		Long result = 0L;
		SystemProperty prop = propertyRepo.findOne(propertyId);
		if (prop != null) {
			result = prop.getNumberValue();
		}
		return result;		
	}
	public int getInt(String propertyId) {
		int result = 0;
		SystemProperty prop = propertyRepo.findOne(propertyId);
		if (prop != null) {
			result = (int) (long) prop.getNumberValue();
		}
		return result;		
	}
	public Long getLongMillis(String propertyId) {
		Long result = 0L;
		SystemProperty prop = propertyRepo.findOne(propertyId);
		if (prop != null) {
			result = prop.getNumberValue() * 1000;
		}
		return result;
	}
	public void updateStringValue(String propertyId, String newValue) {
		SystemProperty prop = propertyRepo.findOne(propertyId);
		if (prop != null) {
			prop.setStringValue(newValue);
			propertyRepo.save(prop);
		}
	}
}
