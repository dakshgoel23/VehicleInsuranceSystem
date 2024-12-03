package com.springboot.VehicleInsuranceSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.VehicleInsuranceSystem.model.Executive;
import com.springboot.VehicleInsuranceSystem.model.User;


public interface ExecutiveRepository extends JpaRepository<Executive, Integer>{

	@Query("select e from Executive e where e.user=?1")
	Executive getExecutiveDetails(User user);

}
