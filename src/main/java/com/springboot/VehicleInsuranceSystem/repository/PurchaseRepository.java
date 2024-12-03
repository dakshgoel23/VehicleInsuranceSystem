package com.springboot.VehicleInsuranceSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.VehicleInsuranceSystem.model.Customer;
import com.springboot.VehicleInsuranceSystem.model.CustomerHasPolicy;
import com.springboot.VehicleInsuranceSystem.model.Policy;

public interface PurchaseRepository extends JpaRepository<CustomerHasPolicy, Integer>{

	@Query("select p from CustomerHasPolicy c join c.policy p where c.customer=?1")
	List<Policy> findbyCustomer(Customer customer);

}
