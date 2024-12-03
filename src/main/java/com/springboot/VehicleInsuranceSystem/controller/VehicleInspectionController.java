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
import com.springboot.VehicleInsuranceSystem.enums.InspectionStatus;
import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.Executive;
import com.springboot.VehicleInsuranceSystem.model.Vehicle;
import com.springboot.VehicleInsuranceSystem.model.VehicleInspection;
import com.springboot.VehicleInsuranceSystem.service.ExecutiveService;
import com.springboot.VehicleInsuranceSystem.service.VehicleInspectionService;
import com.springboot.VehicleInsuranceSystem.service.VehicleService;





@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class VehicleInspectionController {
	
	@Autowired
	private VehicleInspectionService vehicleInspectionService;
	
	@Autowired
	private ExecutiveService executiveService;
	
	@Autowired
	private VehicleService vehicleService;
	

	
	
	@PostMapping("/vehicle-inspection/book")
	public ResponseEntity<?> bookInspection(@RequestParam int vehicle_id,ResponseMessageDto dto,
			                                 @RequestBody VehicleInspection vehicleInspection)
			                                 
	{
		
		Vehicle vehicle=null;
		try
		{
			vehicle=vehicleService.validate(vehicle_id);
		}
		catch(ResourceNotFoundException e)
		{
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
		vehicleInspection.setVehicle(vehicle);
		vehicleInspection.setStatus(InspectionStatus.BOOKED);
		vehicleInspection= vehicleInspectionService.insert(vehicleInspection);
		return ResponseEntity.ok(vehicleInspection);
		
		
	}
	
	@PostMapping("/vehicle-inspection/completed")
	public ResponseEntity<?> inspectionCompleted(@RequestParam int executive_id,@RequestParam int vehicleInspectionId,
			                                @RequestBody VehicleInspection newvehicleInspection, 
			                                ResponseMessageDto dto) {
		
		Executive executive=null;
		VehicleInspection existingVehicleInspection=null;
		try
		{
			executive=executiveService.validate(executive_id);
			existingVehicleInspection=vehicleInspectionService.validate(vehicleInspectionId);
			
			
			if(newvehicleInspection.getInspectionReport()!=null)
			{
				existingVehicleInspection.setInspectionReport(newvehicleInspection.getInspectionReport());
			}
			if(newvehicleInspection.getVehicle_condition()!=null)
			{
				existingVehicleInspection.setVehicle_condition(newvehicleInspection.getVehicle_condition());
			}
			if(newvehicleInspection.getDriver_profile()!=null)
			{
				existingVehicleInspection.setDriver_profile(newvehicleInspection.getDriver_profile());
			}
			
			existingVehicleInspection.setStatus(InspectionStatus.COMPLETED);
			existingVehicleInspection.setExecutive(executive);
			existingVehicleInspection=vehicleInspectionService.insert(existingVehicleInspection);
			return ResponseEntity.ok(existingVehicleInspection);
			
		}
		catch(ResourceNotFoundException e)
		{
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
		
	}
	
	//this API will return all vehicles whose inspection is booked/completed
	@GetMapping("/vehicle/vehicle-inspection/book")
	public ResponseEntity<?> showVehiclesInspected(@RequestParam String status) {
		
		
		try
		{
		InspectionStatus pstatus=InspectionStatus.valueOf(status);
		List<VehicleInspection> list=vehicleInspectionService.showVehiclesInspectionBooked(pstatus);
		return ResponseEntity.ok(list);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
				
	}
	
	@PostMapping("/inspection/changestatus")
	public VehicleInspection changeStatus(@RequestParam int inspection_id) throws ResourceNotFoundException {
		VehicleInspection vehicleInspection=vehicleInspectionService.validate(inspection_id);
		 vehicleInspection.setStatus(InspectionStatus.VIEWED);
		return vehicleInspectionService.insert(vehicleInspection);
	}
	
	
	

}
