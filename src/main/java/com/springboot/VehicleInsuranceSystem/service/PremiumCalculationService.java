package com.springboot.VehicleInsuranceSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.VehicleInsuranceSystem.model.PremiumCalculation;
import com.springboot.VehicleInsuranceSystem.repository.PremiumCalculationRepository;

@Service
public class PremiumCalculationService {
	@Autowired
	private PremiumCalculationRepository premiumCalculationRepository;
	
	public PremiumCalculation insert(PremiumCalculation premiumCalculation)
	{
		return premiumCalculationRepository.save(premiumCalculation);
	}

}
