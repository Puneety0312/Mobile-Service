package com.infy.model;

import java.util.List;

public class ServiceReport {
	private int serviceId;
	private String brand;
	private List<String> issues;
	private float serviceFee;
	
	public ServiceReport() {
		// TODO Auto-generated constructor stub
	}
	
	public ServiceReport(int serviceId,String brand,List<String> issues,float serviceFee) {
		// TODO Auto-generated constructor stub
		this.brand=brand;
		this.issues=issues;
		this.serviceFee=serviceFee;
		this.serviceId=serviceId;
	}

	public int getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public List<String> getIssues() {
		return this.issues;
	}

	public void setIssues(List<String> issues) {
		this.issues = issues;
	}

	public float getServiceFee() {
		return this.serviceFee;
	}

	public void setServiceFee(float serviceFee) {
		this.serviceFee = serviceFee;
	}
	
}
