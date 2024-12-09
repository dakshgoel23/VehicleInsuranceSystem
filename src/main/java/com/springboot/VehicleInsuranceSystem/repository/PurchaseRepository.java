package com.springboot.VehicleInsuranceSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.VehicleInsuranceSystem.model.Customer;
import com.springboot.VehicleInsuranceSystem.model.CustomerHasPolicy;

public interface PurchaseRepository extends JpaRepository<CustomerHasPolicy, Integer>{

	@Query("select c from CustomerHasPolicy c where c.customer=?1")
	List<CustomerHasPolicy> findbyCustomer(Customer customer);

}
