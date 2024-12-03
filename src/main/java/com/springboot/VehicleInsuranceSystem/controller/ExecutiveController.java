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
import com.springboot.VehicleInsuranceSystem.dto.UpdateProfileExecutiveDto;
import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.Executive;
import com.springboot.VehicleInsuranceSystem.model.User;
import com.springboot.VehicleInsuranceSystem.service.ExecutiveService;
import com.springboot.VehicleInsuranceSystem.service.UserService;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class ExecutiveController {
	
	@Autowired
	private ExecutiveService executiveService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/executive/add")
	public ResponseEntity<?> addexecutive(@RequestParam int user_id,
			                        @RequestBody Executive executive, ResponseMessageDto dto) throws ResourceNotFoundException
	{
		
		User user = null;
		user = userService.validate(user_id);

		executive.setUser(user);

		executive = executiveService.insert(executive);
		return ResponseEntity.ok(executive);
		
	}
	
	
	@GetMapping("/executive/viewall")
	public List<Executive> getAll()
	{
		List<Executive> list=executiveService.viewall();
		return list;
	}
	
	@GetMapping("/executive/details/byId")
	public Executive getexecutive(@RequestParam int executive_id) throws ResourceNotFoundException
	{
		return executiveService.findExecutive(executive_id);
	}
	
	
	@PostMapping("/executive/update")
	public ResponseEntity<?> updateProfile(@RequestParam int executive_id,
            @RequestBody UpdateProfileExecutiveDto profileDTO) throws ResourceNotFoundException {

        Executive updatedExecutive = executiveService.updatedExecutiveProfile(executive_id, profileDTO);

        return ResponseEntity.ok(updatedExecutive);
	}
	
	@GetMapping("/api/get/executive")
	public Executive getExecutive(@RequestParam int user_id) throws ResourceNotFoundException {
		return executiveService.getExecutiveDetails(user_id);
	}
}
