package com.springboot.VehicleInsuranceSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.Vehicle;
import com.springboot.VehicleInsuranceSystem.repository.VehicleRepository;



@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepository;

	public Vehicle addVehicle(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	public List<Vehicle> getVehicleDetails(int customerId) {
		return vehicleRepository.findbyCustomer(customerId);
	}
	
	public Vehicle validate(int id) throws ResourceNotFoundException {
		Optional<Vehicle> optional = vehicleRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Vehicle id invalid");
		
		Vehicle vehicle = optional.get();
		return vehicle; 
		
	}

	public List<Integer> getVehicleIds(int customer_id) {
		return vehicleRepository.findByCustomerId(customer_id);
	}
	

}