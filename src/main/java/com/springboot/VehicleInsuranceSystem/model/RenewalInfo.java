package com.springboot.VehicleInsuranceSystem.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class RenewalInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
	@ManyToOne
	private CustomerHasPolicy customerHasPolicy;

	private LocalDate previousDateOfPurchase; 
	
	private LocalDate currentDateOfPurchase; 
	
	private double previousPremium; 
	
	private double currentPremium; 
	
	@ManyToOne
	private Executive executive;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getPreviousDateOfPurchase() {
		return previousDateOfPurchase;
	}
	public void setPreviousDateOfPurchase(LocalDate previousDateOfPurchase) {
		this.previousDateOfPurchase = previousDateOfPurchase;
	}
	public LocalDate getCurrentDateOfPurchase() {
		return currentDateOfPurchase;
	}
	public void setCurrentDateOfPurchase(LocalDate currentDateOfPurchase) {
		this.currentDateOfPurchase = currentDateOfPurchase;
	}
	public double getPreviousPremium() {
		return previousPremium;
	}
	public void setPreviousPremium(double previousPremium) {
		this.previousPremium = previousPremium;
	}
	public double getCurrentPremium() {
		return currentPremium;
	}
	public void setCurrentPremium(double currentPremium) {
		this.currentPremium = currentPremium;
	}
	public Executive getExecutive() {
		return executive;
	}
	public void setExecutive(Executive executive) {
		this.executive = executive;
	} 
	
	
	public CustomerHasPolicy getCustomerHasPolicy() {
		return customerHasPolicy;
	}
	public void setCustomerHasPolicy(CustomerHasPolicy customerHasPolicy) {
		this.customerHasPolicy = customerHasPolicy;
	}

}
