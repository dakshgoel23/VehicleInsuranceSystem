package com.springboot.VehicleInsuranceSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(int id, String address, String city, String pincode) {
		super();
		this.id = id;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
	}

	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String address;
	private String city;
	
	private String pincode;
	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	

}
