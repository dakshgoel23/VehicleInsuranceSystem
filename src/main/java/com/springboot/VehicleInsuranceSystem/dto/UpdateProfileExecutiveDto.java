package com.springboot.VehicleInsuranceSystem.dto;

import org.springframework.stereotype.Component;

import com.springboot.VehicleInsuranceSystem.model.Address;

@Component
public class UpdateProfileExecutiveDto {
	
	    private String name;
	    private String phone;
	    private Address address;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}

}
