package com.springboot.VehicleInsuranceSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.VehicleInsuranceSystem.model.Customer;
import com.springboot.VehicleInsuranceSystem.model.CustomerHasPolicy;
import com.springboot.VehicleInsuranceSystem.model.Policy;
import com.springboot.VehicleInsuranceSystem.repository.PurchaseRepository;

@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseRepository purchaseRepository;

	public CustomerHasPolicy insert(CustomerHasPolicy customerHasPolicy) {
		return purchaseRepository.save(customerHasPolicy);
	}

	public List<Policy> getPoliciesByCustomer(Customer customer) {
		return purchaseRepository.findbyCustomer(customer);
	}

	

}
