package com.infy.validator;

import java.util.List;


import com.infy.exception.MobileServiceException;
import com.infy.model.ServiceRequest;

public class Validator {
	ServiceRequest service ;
	public void validate(ServiceRequest service) throws MobileServiceException{	
		//your code goes here
		this.service=service;
		if(!isValidBrand(service.getBrand())) {
			throw new MobileServiceException("Validator.INVALID_BRAND");
		}
		if(!isValidIssues(service.getIssues())) throw new MobileServiceException(" Validator.INVALID_NO_OF_ISSUES");
		if(!isValidContactNumber(service.getContactNumber())) throw new MobileServiceException("Validator.INVALID_CONTACT_NUMBER");
		
		if(!isValidIMEINumber(service.getiMEINumber())) throw new MobileServiceException("Validator.INVALID_IMEI_NUMBER");
		if(!isValidCustomerName(service.getCustomerName())) throw new MobileServiceException("Validator.INVALID_CUSTOMER_NAME");
		
	}	
	
	
	// validates the brand
	// brand should always start with a upper case alphabet 
	// and can be followed by one or more alphabets (lower case or upper case) 
	public Boolean isValidBrand(String brand){
		if(Character.isUpperCase(brand.charAt(0))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	// validates the list of issues
	// checks if the list is null or empty
	public Boolean isValidIssues(List<String> issues) {
		return !(issues==null || issues.isEmpty());
	}

	// validates the IMEINumber
	// it should be a 16 digit number 
	public Boolean isValidIMEINumber(Long iMEINumber) {
		String s= iMEINumber.toString();
		return s.length()==16;
	}
	
	// validates the contact number
	// should contain 10 numeric characters and should not contain 10 repetitive characters
	public Boolean isValidContactNumber(Long contactNumber) {
		String c = contactNumber.toString();
		if(c.matches("(\\D?[0-9]{3}\\D?)[\\s][0-9]{3}-[0-9]{4}") && c.matches("\\d{10}"))
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	
	// validates the customer name
	// should contain at least one word and each word separated by a single space should contain at least one letter.
	// the first letter of every word should be an upper case character 
	public Boolean isValidCustomerName(String customerName) {
		if(customerName.matches("\\[A-Za-z]+") && Character.isUpperCase(customerName.charAt(0))) {
			return true;
		}
		else {
			return false;
		}
		
	}
}
