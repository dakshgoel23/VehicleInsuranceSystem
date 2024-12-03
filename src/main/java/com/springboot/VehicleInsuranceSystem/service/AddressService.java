package com.springboot.VehicleInsuranceSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.Address;
import com.springboot.VehicleInsuranceSystem.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;

	public Address insert(Address address) {
		return addressRepository.save(address);
	}
	
      public Address validate(int id) throws ResourceNotFoundException {
		
		Optional< Address> optional=addressRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Id invalid");
		
		 Address address = optional.get();
		return address;  
		
		
	}
      
      public Address getById(int id) throws ResourceNotFoundException {
  		Optional<Address> optional = addressRepository.findById(id);
  		if(optional.isEmpty())
  			throw new ResourceNotFoundException("Address id Invalid");
  		
  		return optional.get();
  	}

}
