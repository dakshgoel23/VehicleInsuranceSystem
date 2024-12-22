package com.springboot.VehicleInsuranceSystem.controller;

import java.time.LocalDate;
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
import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.Customer;
import com.springboot.VehicleInsuranceSystem.model.CustomerHasPolicy;
import com.springboot.VehicleInsuranceSystem.model.Policy;
import com.springboot.VehicleInsuranceSystem.service.CustomerService;
import com.springboot.VehicleInsuranceSystem.service.PolicyService;
import com.springboot.VehicleInsuranceSystem.service.PurchaseService;


@RestController
@CrossOrigin(origins = {"http://localhost:4210"})
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PolicyService policyService;

	
	@PostMapping("/purchase/policy")
	public ResponseEntity<?> postMethodName(@RequestParam int customer_id, @RequestParam int policy_id,
			                   @RequestBody CustomerHasPolicy customerHasPolicy,ResponseMessageDto dto) throws ResourceNotFoundException 
	{

		Customer customer=null;
		customer=customerService.validate(customer_id);
		
		Policy policy=null;
	    policy=policyService.validate(policy_id);
		
		
		customerHasPolicy.setDate_of_purchase(LocalDate.now());
		customerHasPolicy.setDate_of_expiry(LocalDate.now().plusYears(1).plusDays(8));
		customerHasPolicy.setCustomer(customer);
		customerHasPolicy.setPolicy(policy);
		
		
		
		customerHasPolicy=purchaseService.insert(customerHasPolicy);
		
		return ResponseEntity.ok(customerHasPolicy);
		
	
		
	}
	
	
	//view all active policies purchased by customer
	@GetMapping("/purchase/policy/byCustomer")
	public ResponseEntity<?> getPoliciesByCustomer(@RequestParam int customer_id,
			ResponseMessageDto dto)
	{
		Customer customer=null;
		try
		{
			customer=customerService.validate(customer_id);
		}
		catch(ResourceNotFoundException e)
		{
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
		List<CustomerHasPolicy> list=purchaseService.getPoliciesByCustomer(customer);
		return ResponseEntity.ok(list);
		
		
	}
	
	
}
