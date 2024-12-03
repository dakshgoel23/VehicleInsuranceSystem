package com.springboot.VehicleInsuranceSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.VehicleInsuranceSystem.model.Customer;
import com.springboot.VehicleInsuranceSystem.model.User;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("select c from CustomerHasPolicy chp join chp.customer c where chp.isActive=?1")
	List<Customer> getActiveCustomers(boolean is_active);

	@Query(value="select p.name,p.premium_amount,p.type,p.duration_months,p.coverage_amount from policy p join vehicle v on p.vehicle_id=v.id join customer c on c.id=v.customer_id where c.id=?1;",nativeQuery = true)
	List<Object> findAllProposedPolicies(int customer_id);

	@Query("select c from Customer c where c.user=?1")
	Customer getCustomerDetails(User user);


}
