package com.springboot.VehicleInsuranceSystem.model;

import com.springboot.VehicleInsuranceSystem.enums.FuelType;
import com.springboot.VehicleInsuranceSystem.enums.VehicleCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Vehicle {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(unique = true)
	private String registration_number;
	
	private String manufacturer;
	private String model;
	
	@Enumerated(EnumType.STRING)
	private FuelType fuel_type;
	
	@Enumerated(EnumType.STRING)
	private VehicleCategory category;

	@ManyToOne
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegistration_number() {
		return registration_number;
	}

	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}


	public FuelType getFuel_type() {
		return fuel_type;
	}

	public void setFuel_type(FuelType fuel_type) {
		this.fuel_type = fuel_type;
	}

	public VehicleCategory getCategory() {
		return category;
	}

	public void setCategory(VehicleCategory category) {
		this.category = category;
	}

	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vehicle(int id, String registration_number, String manufacturer, String model, FuelType fuel_type,
			VehicleCategory category, Customer customer) {
		super();
		this.id = id;
		this.registration_number = registration_number;
		this.manufacturer = manufacturer;
		this.model = model;
		this.fuel_type = fuel_type;
		this.category = category;
		this.customer = customer;
	}
	
	


	

}
