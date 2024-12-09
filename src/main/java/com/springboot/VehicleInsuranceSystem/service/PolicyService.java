package com.springboot.VehicleInsuranceSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.VehicleInsuranceSystem.enums.PolicyStatus;
import com.springboot.VehicleInsuranceSystem.enums.PolicyType;
import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.Policy;
import com.springboot.VehicleInsuranceSystem.repository.PolicyRepository;


@Service
public class PolicyService {
	
	@Autowired
	private PolicyRepository policyRepository;
	
	public Policy insert(Policy policy) {
		return policyRepository.save(policy);
		
	}

	public List<Policy> getAllPolicy() {
		 
		return policyRepository.findAll();
	}

	public void delete(int id) {
		policyRepository.deleteById(id);
		
	}

	public Policy validate(int id) throws ResourceNotFoundException {
		Optional<Policy> optional = policyRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("policy id invalid");
		
		Policy policy = optional.get();
		return policy; 
		
	}

	public List<Policy> getPoliciesByType(PolicyType type) {
		return policyRepository.findByType(type);
	}

	public List<Policy> getPolicyByPremiumAmount(double amt) {

		return policyRepository.getPolicyByPremiumAmount(amt);
	}

	public List<Policy> getPolicies(PolicyStatus status) {
		return policyRepository.getPolicyByStatus(status);
	}


}
