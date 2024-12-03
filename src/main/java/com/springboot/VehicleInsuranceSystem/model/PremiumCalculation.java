package com.springboot.VehicleInsuranceSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PremiumCalculation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private double basePremium;
	private double vehicleRiskFactor;
	private double driverRiskFactor;
	private double priceOfCoverage;
	private double discount;
	
	public double getDriverRiskFactor() {
		return driverRiskFactor;
	}
	public void setDriverRiskFactor(double driverRiskFactor) {
		this.driverRiskFactor = driverRiskFactor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBasePremium() {
		return basePremium;
	}
	public void setBasePremium(double basePremium) {
		this.basePremium = basePremium;
	}
	public double getVehicleRiskFactor() {
		return vehicleRiskFactor;
	}
	public void setVehicleRiskFactor(double vehicleRiskFactor) {
		this.vehicleRiskFactor = vehicleRiskFactor;
	}
	public double getPriceOfCoverage() {
		return priceOfCoverage;
	}
	public void setPriceOfCoverage(double priceOfCoverage) {
		this.priceOfCoverage = priceOfCoverage;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	
	

}
