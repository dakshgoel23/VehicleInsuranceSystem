package com.springboot.VehicleInsuranceSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.VehicleInsuranceSystem.enums.InspectionStatus;
import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.VehicleInspection;
import com.springboot.VehicleInsuranceSystem.repository.VehicleInspectionRepository;

@Service
public class VehicleInspectionService {
	@Autowired
	private VehicleInspectionRepository vehicleInspectionRepository;

	public VehicleInspection insert(VehicleInspection vehicleInspection) {
		return vehicleInspectionRepository.save(vehicleInspection);
	}
	
	public VehicleInspection validate(int id) throws ResourceNotFoundException {
		Optional<VehicleInspection> optional = vehicleInspectionRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Inspection id invalid");
		
		VehicleInspection vehicleInspection = optional.get();
		return vehicleInspection; 
		
	}

	public List<VehicleInspection> showVehiclesInspectionBooked(InspectionStatus status) {
		return vehicleInspectionRepository.showInspectionbooked(status);
	}

}
