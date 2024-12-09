package com.springboot.VehicleInsuranceSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.VehicleInsuranceSystem.dto.ResponseMessageDto;
import com.springboot.VehicleInsuranceSystem.enums.PolicyStatus;
import com.springboot.VehicleInsuranceSystem.enums.PolicyType;
import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.Policy;
import com.springboot.VehicleInsuranceSystem.model.PremiumCalculation;
import com.springboot.VehicleInsuranceSystem.model.Vehicle;
import com.springboot.VehicleInsuranceSystem.service.PolicyService;
import com.springboot.VehicleInsuranceSystem.service.PremiumCalculationService;
import com.springboot.VehicleInsuranceSystem.service.VehicleService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class PolicyController {

	@Autowired
	private PolicyService policyService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private PremiumCalculationService premiumCalculationService;
	
	@PostMapping("/policy/add")
	public ResponseEntity<?> addPolicy(@RequestParam int vehicle_id,
			                @RequestBody Policy policy,ResponseMessageDto dto) throws ResourceNotFoundException {
		Vehicle vehicle=null;
        vehicle=vehicleService.validate(vehicle_id);
        PremiumCalculation premiumCalculation=policy.getPremiumCalculation();
        premiumCalculation=premiumCalculationService.insert(premiumCalculation);
        policy.setPremiumCalculation(premiumCalculation);
        policy.setVehicle(vehicle);
        policy.setStatus(PolicyStatus.PROPOSED);
        policy=policyService.insert(policy);
		return ResponseEntity.ok(policy);
		
	}
	
	@GetMapping("/policy/all")
	public List<Policy> getAllPolicy() {
		List<Policy> list = policyService.getAllPolicy();
		return list;
	}
	
	@DeleteMapping("/policy/delete")
	public ResponseEntity<?> deletePolicy(@RequestParam int id, ResponseMessageDto dto) {

		try {
			policyService.validate(id);
			policyService.delete(id);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		} 
		dto.setMsg("policy deleted");
		return ResponseEntity.ok(dto);
		
	}
	
	
	@PutMapping("/policy/update")
	public ResponseEntity<?> updatePolicy(@RequestParam int id, @RequestBody Policy newPolicy,ResponseMessageDto dto) {
		try {
			Policy existingPolicyDb = policyService.validate(id);
			if(newPolicy.getName() != null)
				existingPolicyDb.setName(newPolicy.getName());
			
			if(newPolicy.getPremium_amount() != 0)
				existingPolicyDb.setPremium_amount(newPolicy.getPremium_amount());
			
			if(newPolicy.getCoverage_amount() != 0)
				existingPolicyDb.setCoverage_amount(newPolicy.getCoverage_amount());
			
			if(newPolicy.getDuration_months() != 0)
				existingPolicyDb.setDuration_months(newPolicy.getDuration_months());
			
			
			if(newPolicy.getType() != null)
				existingPolicyDb.setType(newPolicy.getType());
			
			//re save this existing policy having new updated value 
			existingPolicyDb = policyService.insert(existingPolicyDb);
			return ResponseEntity.ok(existingPolicyDb);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	
	@GetMapping("/policy/type/get")//DoneByMe
	public ResponseEntity<?> gePoliciesByType(@RequestParam String type)
	{
		try
		{
		PolicyType ptype=PolicyType.valueOf(type);
		List<Policy> list= policyService.getPoliciesByType(ptype);
		return ResponseEntity.ok(list);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping("/policy/premium/get")
	public ResponseEntity<?> getPolicyByPremiumAmount(@RequestParam String amount) {
		try {
			double amt = Double.parseDouble(amount);
			List<Policy> list = policyService.getPolicyByPremiumAmount(amt);
			return ResponseEntity.ok(list);
		}
		catch(NumberFormatException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/policy/byStatus")
	public List<Policy> getPolicyByStatus(@RequestParam PolicyStatus status) {
		return policyService.getPolicies(status);
	}
	
	@PostMapping("/policy/accept")
	public void acceptPolicy(@RequestParam int policy_id) throws ResourceNotFoundException {
		Policy policy=policyService.validate(policy_id);
		policy.setStatus(PolicyStatus.ACCEPTED);
		policyService.insert(policy);
	}
	
	@PostMapping("/policy/reject")
	public void rejectPolicy(@RequestParam int policy_id) throws ResourceNotFoundException {
		Policy policy=policyService.validate(policy_id);
		policy.setStatus(PolicyStatus.REJECTED);
		policyService.insert(policy);
	}
	
	
	@GetMapping("/policy/enumType/get")
	public ResponseEntity<PolicyType[]> getAllPolicyTypes() {
       
        return ResponseEntity.ok(PolicyType.values());
    }
	
	
	
	
	
	
	
}