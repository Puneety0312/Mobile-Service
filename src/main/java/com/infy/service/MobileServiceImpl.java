package com.infy.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.LogFactory;

import com.infy.dao.MobileServiceDAO;
import com.infy.dao.MobileServiceDAOImpl;
import com.infy.exception.MobileServiceException;
import com.infy.model.ServiceReport;
import com.infy.model.ServiceRequest;
import com.infy.model.Status;
import com.infy.validator.Validator;

public class MobileServiceImpl implements MobileService{
	
	//private static final LocalDateTime LocalDateTime = ;

	private MobileServiceDAO dao=new MobileServiceDAOImpl();
	
	private Validator validator=new Validator();
	
	
	@Override
	public ServiceRequest registerRequest(ServiceRequest service) throws MobileServiceException {
		ServiceRequest serviceDAO;
		try {
			validator.validate(service);
			float cost = calculateEstimateCost(service.getIssues());
			if(cost<=0) {
				throw new MobileServiceException("Service.INVALID_ISSUES");
				
			}
			else {
				service.setServiceFee(cost);
				service.setStatus(Status.ACCEPTED);
				service.setTimeOfRequest(LocalDateTime.now());
				serviceDAO = registerRequest(service);
				return serviceDAO;
			}
		} catch (MobileServiceException mobileServiceException) {
			if(mobileServiceException.getMessage().startsWith("Service")) {
				LogFactory.getLog(getClass()).error(mobileServiceException.getMessage());
			}
			throw mobileServiceException;
			// TODO: handle exception
		}
		//return service;
		
	}

	@Override
	public Float calculateEstimateCost(List<String> issues) throws MobileServiceException {
		Float totalCost=0.0F;
		for(String issue: issues) {
			if(issue.equalsIgnoreCase("BATTERY")) {
				totalCost +=10;
			}
			else if(issue.equalsIgnoreCase("CAMERA")){
				totalCost +=5;
			}
			else if(issue.equalsIgnoreCase("SCREEN")) {
				totalCost +=15;
			}
		}
		return totalCost;
	}

	@Override
	public List<ServiceReport> getServices(Status status) throws MobileServiceException {
		List<ServiceReport> ServiceReportList = new ArrayList<>();
		try {
			ServiceReportList=dao.getServices()												//getting error
					.parallelStream()
					.filter(serviceRequest -> serviceRequest.getStatus().equals(status))
					.map(serviceRequest -> new ServiceReport())						
					.collect(Collectors.toList());
			if(ServiceReportList.isEmpty()){
				throw new MobileServiceException("Sorry, we did not find any records for your query");
			}
			
		}
		catch(MobileServiceException mobileServiceException) {
			if(mobileServiceException.getMessage().startsWith("service")) {
				LogFactory.getLog(getClass()).error(mobileServiceException.getMessage());	//getting error
			}
			throw mobileServiceException;
		}
		return ServiceReportList;
	}

}
