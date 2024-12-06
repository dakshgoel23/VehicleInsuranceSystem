package com.springboot.VehicleInsuranceSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.VehicleInsuranceSystem.dto.ResponseMessageDto;
import com.springboot.VehicleInsuranceSystem.dto.UpdateProfileCustomerDto;
import com.springboot.VehicleInsuranceSystem.enums.PolicyStatus;
import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.Address;
import com.springboot.VehicleInsuranceSystem.model.Customer;
import com.springboot.VehicleInsuranceSystem.model.User;
import com.springboot.VehicleInsuranceSystem.repository.AddressRepository;
import com.springboot.VehicleInsuranceSystem.service.AddressService;
import com.springboot.VehicleInsuranceSystem.service.CustomerService;
import com.springboot.VehicleInsuranceSystem.service.UserService;



@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressRepository addressRepository;
	
	
	@PostMapping("/customer/add")
	public ResponseEntity<?> addcustomer(@RequestParam int user_id, 
			                        @RequestBody Customer customer, ResponseMessageDto dto) throws ResourceNotFoundException
	{
		User user=null;
		Address address=null;
		user=userService.validate(user_id);
		customer.setUser(user);
	    address=addressRepository.save(customer.getAddress());
		customer.setAddress(address);
		customer=customerService.insert(customer);
	    return ResponseEntity.ok(customer);
	 
		
	}
	
	
	@GetMapping("/customer/viewall")
	public List<Customer> getAll()
	{
		List<Customer> list=customerService.viewall();
		return list;
	}
	
	//this API will show all customers who have active policy
	@GetMapping("/customer/active")
	public ResponseEntity<?> getAllActiveCustomers(@RequestParam boolean is_active)
	{
		List<Customer> list= customerService.getAllActiveCustomers(is_active);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/customer/proposedpolicies")
	public ResponseEntity<?> seeAllProposedPolicies(@RequestParam int customer_id,@RequestParam PolicyStatus status, ResponseMessageDto dto)
	{
//		Customer customer=null;
//		try
//		{
//			customer=customerService.validate(customer_id);
//		}
//		catch(ResourceNotFoundException e)
//		{
//			dto.setMsg(e.getMessage());
//			return ResponseEntity.badRequest().body(dto);
//			
//		}
		
		List<Object> list=customerService.findallproposedpolicies(customer_id,status);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/customer/details/byId")
	public Customer getcustomer(@RequestParam int customer_id) throws ResourceNotFoundException
	{
		return customerService.findCustomer(customer_id);
	}
	
	
	@PostMapping("/customer/update")
	public ResponseEntity<?> updateProfile(@RequestParam int customer_id,
            @RequestBody UpdateProfileCustomerDto profileDTO) throws ResourceNotFoundException {

        // Call the service to update the profile
        Customer updatedCustomer = customerService.updateCustomerProfile(customer_id, profileDTO);

        return ResponseEntity.ok(updatedCustomer);
	}
	
	
	@GetMapping("/api/get/customer")
	public Customer getCustomer(@RequestParam int user_id) throws ResourceNotFoundException {
		return customerService.getCustomerDetails(user_id);
	}
	

	
}
