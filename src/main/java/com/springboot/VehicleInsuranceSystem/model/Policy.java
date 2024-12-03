package com.springboot.VehicleInsuranceSystem.model;

import com.springboot.VehicleInsuranceSystem.enums.PolicyStatus;
import com.springboot.VehicleInsuranceSystem.enums.PolicyType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Policy {
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private double premium_amount;
	private double coverage_amount;
	private int duration_months;
	
	@Enumerated(EnumType.STRING)
	private PolicyType type;
	
	@OneToOne
	private Vehicle vehicle;
	
	@Enumerated(EnumType.STRING)
	private PolicyStatus status; //PROPOSED,ACCEPTED,REJECTED
	
	@OneToOne
	private PremiumCalculation premiumCalculation;

	public PremiumCalculation getPremiumCalculation() {
		return premiumCalculation;
	}

	public void setPremiumCalculation(PremiumCalculation premiumCalculation) {
		this.premiumCalculation = premiumCalculation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPremium_amount() {
		return premium_amount;
	}

	public void setPremium_amount(double premium_amount) {
		this.premium_amount = premium_amount;
	}

	public double getCoverage_amount() {
		return coverage_amount;
	}

	public void setCoverage_amount(double coverage_amount) {
		this.coverage_amount = coverage_amount;
	}

	public int getDuration_months() {
		return duration_months;
	}

	public void setDuration_months(int duration_months) {
		this.duration_months = duration_months;
	}

	public PolicyType getType() {
		return type;
	}

	public void setType(PolicyType type) {
		this.type = type;
	}



	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public PolicyStatus getStatus() {
		return status;
	}

	public void setStatus(PolicyStatus status) {
		this.status = status;
	}
	
	

}
