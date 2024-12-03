package com.springboot.VehicleInsuranceSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.VehicleInsuranceSystem.model.PremiumCalculation;

public interface PremiumCalculationRepository extends JpaRepository<PremiumCalculation, Integer>{

}
