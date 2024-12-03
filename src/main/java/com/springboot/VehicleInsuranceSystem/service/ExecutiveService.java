package com.springboot.VehicleInsuranceSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.VehicleInsuranceSystem.dto.UpdateProfileExecutiveDto;
import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.Address;
import com.springboot.VehicleInsuranceSystem.model.Executive;
import com.springboot.VehicleInsuranceSystem.model.User;
import com.springboot.VehicleInsuranceSystem.repository.AddressRepository;
import com.springboot.VehicleInsuranceSystem.repository.ExecutiveRepository;
import com.springboot.VehicleInsuranceSystem.repository.UserRepository;

@Service
public class ExecutiveService {

	@Autowired
	private ExecutiveRepository executiveRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserRepository userRepository;
	
	public Executive insert(Executive executive) {
		Address address=null;
		address=executive.getAddress();
		address=addressRepository.save(address);
		executive.setAddress(address);
		return executiveRepository.save(executive);
	}

	public List<Executive> viewall() {
		return executiveRepository.findAll();
	}
	
     public Executive validate(int id) throws ResourceNotFoundException {
		
		Optional< Executive> optional=executiveRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Executive id invalid");
		
		 Executive executive = optional.get();
		return executive;  
		
		
	}
	
	public Executive findExecutive(int executive_id) throws ResourceNotFoundException {
		Optional< Executive> optional=executiveRepository.findById(executive_id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Invalid id ");
		
		 Executive executive = optional.get();
		return executive;  
	}

	public Executive updatedExecutiveProfile(int executive_id, UpdateProfileExecutiveDto profileDTO) throws ResourceNotFoundException {
		 Executive executive = validate(executive_id);

	        executive.setName(profileDTO.getName());
	        executive.setPhone(profileDTO.getPhone());
	        Address address=null;
			if (executive.getAddress() != null) {
				 address = executive.getAddress();
			        address.setAddress(profileDTO.getAddress().getAddress());
			        address.setCity(profileDTO.getAddress().getCity());
			        address.setPincode(profileDTO.getAddress().getPincode());
			} else {
				address=addressRepository.save(profileDTO.getAddress());
	        }
	        executive.setAddress(address);

	        return executiveRepository.save(executive);
	}

	public Executive getExecutiveDetails(int user_id) throws ResourceNotFoundException {
		Optional<User> optional= userRepository.findById(user_id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("User id invalid");
		
		User user=optional.get();
		return executiveRepository.getExecutiveDetails(user);
	}
	

}
