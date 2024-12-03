package com.springboot.VehicleInsuranceSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.VehicleInsuranceSystem.enums.PolicyStatus;
import com.springboot.VehicleInsuranceSystem.enums.PolicyType;
import com.springboot.VehicleInsuranceSystem.model.Policy;


public interface PolicyRepository extends JpaRepository<Policy, Integer>{
//
//	List<Policy> findByCategory(VehicleCategory category);


	List<Policy> findByType(PolicyType type);
	
	@Query("select p from Policy p where p.premium_amount>=?1")
	List<Policy> getPolicyByPremiumAmount(double amt);

	List<Policy> getPolicyByStatus(PolicyStatus status);
	
	

}
