package com.springboot.VehicleInsuranceSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.VehicleInsuranceSystem.dto.UpdateProfileCustomerDto;
import com.springboot.VehicleInsuranceSystem.enums.PolicyStatus;
import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.Address;
import com.springboot.VehicleInsuranceSystem.model.Customer;
import com.springboot.VehicleInsuranceSystem.model.User;
import com.springboot.VehicleInsuranceSystem.repository.AddressRepository;
import com.springboot.VehicleInsuranceSystem.repository.CustomerRepository;
import com.springboot.VehicleInsuranceSystem.repository.UserRepository;


@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserRepository userRepository;
	
	public Customer insert(Customer customer) {
		
		
		return customerRepository.save(customer);
	}

	public List<Customer> viewall() {
		return customerRepository.findAll();
	}
	
    public Customer validate(int id) throws ResourceNotFoundException {
		
		Optional< Customer> optional=customerRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Customer id invalid");
		
		 Customer customer = optional.get();
		return customer;  
		
		
	}

	public List<Customer> getAllActiveCustomers(boolean is_active) {
		return customerRepository.getActiveCustomers(is_active);
	}

	public List<Object> findallproposedpolicies(int customer_id,PolicyStatus status) {
		
		return customerRepository.findAllProposedPolicies(customer_id,status);
	}

	public Customer findCustomer(int customer_id) throws ResourceNotFoundException {
		Optional< Customer> optional=customerRepository.findById(customer_id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Invalid id invalid");
		
		 Customer customer = optional.get();
		return customer;  
	}


	public Customer updateCustomerProfile(int customer_id, UpdateProfileCustomerDto profileDTO) throws ResourceNotFoundException {
        // Fetch the customer from the database
        Customer customer = validate(customer_id);

        // Update only the necessary fields
        customer.setName(profileDTO.getName());
        customer.setPhone(profileDTO.getPhone());
        customer.setAadhar_number(profileDTO.getAadhar_number());
        Address address=null;
		if (customer.getAddress() != null) {
			 address = customer.getAddress();
		        address.setAddress(profileDTO.getAddress().getAddress());
		        address.setCity(profileDTO.getAddress().getCity());
		        address.setPincode(profileDTO.getAddress().getPincode());
		} else {
			address=addressRepository.save(profileDTO.getAddress());
        }
        
//         System.out.println(address.getId());
        customer.setAddress(address);
//        customer.getAddress().setCity(profileDTO.getCity());
//        customer.getAddress().setPincode(profileDTO.getPincode());

        // Save the updated customer back to the database
        return customerRepository.save(customer);
    }

	public Customer getCustomerDetails(int user_id) throws ResourceNotFoundException {
		Optional<User> optional= userRepository.findById(user_id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("User id invalid");
		
		User user=optional.get();
		return customerRepository.getCustomerDetails(user);
	}


}
