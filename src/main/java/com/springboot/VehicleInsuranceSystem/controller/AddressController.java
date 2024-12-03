package com.springboot.VehicleInsuranceSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.VehicleInsuranceSystem.model.Address;
import com.springboot.VehicleInsuranceSystem.service.AddressService;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/address/add")
	public Address addaddress(@RequestBody Address address) {
		return addressService.insert(address);
	}
	

}
