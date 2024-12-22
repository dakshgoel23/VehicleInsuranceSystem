package com.springboot.VehicleInsuranceSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.springboot.VehicleInsuranceSystem.model.Vehicle;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

	@Query("select v.id from Vehicle v JOIN v.customer c where c.id=?1")
	List<Integer> findbyCustomer(int customerId);

}
