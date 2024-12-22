package com.springboot.VehicleInsuranceSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.VehicleInsuranceSystem.dto.ResponseMessageDto;
import com.springboot.VehicleInsuranceSystem.enums.FuelType;
import com.springboot.VehicleInsuranceSystem.enums.PolicyType;
import com.springboot.VehicleInsuranceSystem.enums.VehicleCategory;
import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.Customer;
import com.springboot.VehicleInsuranceSystem.model.Vehicle;
import com.springboot.VehicleInsuranceSystem.service.CustomerService;
import com.springboot.VehicleInsuranceSystem.service.VehicleService;




@RestController
@CrossOrigin(origins = {"http://localhost:4210"})
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	@Autowired 
	private CustomerService customerService;
	
	@PostMapping("/vehicle/add")
	public ResponseEntity<?> insert(@RequestParam int customerId,
			@RequestBody Vehicle vehicle,
			ResponseMessageDto dto)
	{
		
		Customer customer = null; 
		try {
			customer =  customerService.validate(customerId);
		} catch (ResourceNotFoundException e) {
			 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
		}
		
		  vehicle.setCustomer(customer);
	      vehicle=vehicleService.addVehicle(vehicle);
	      return ResponseEntity.ok(vehicle);
		
	}
	
	//get all vehicles details owned by a particular customer
	@GetMapping("/vehicle/details/bycustomerId")
	public ResponseEntity<?> getVehicleDetails(@RequestParam int  customerId,ResponseMessageDto dto) {

		try {
			customerService.validate(customerId);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}

		List<Integer> list =  vehicleService.getVehicleDetails(customerId);
		return ResponseEntity.ok(list);
	}
	
	
	@GetMapping("/vehicle/enumCategory/get")
	public ResponseEntity<VehicleCategory[]> getAllVehicleCategory() {
       
        return ResponseEntity.ok(VehicleCategory.values());
    }
	
	@GetMapping("/vehicle/enumFuelType/get")
	public ResponseEntity<FuelType[]> getAllFuelType() {
       
        return ResponseEntity.ok(FuelType.values());
    }
	
	
	

}
