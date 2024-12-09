package com.springboot.VehicleInsuranceSystem.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.VehicleInsuranceSystem.dto.UpdateProfileCustomerDto;
import com.springboot.VehicleInsuranceSystem.enums.PolicyStatus;
import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.Address;
import com.springboot.VehicleInsuranceSystem.model.Customer;
import com.springboot.VehicleInsuranceSystem.model.CustomerDocument;
import com.springboot.VehicleInsuranceSystem.model.User;
import com.springboot.VehicleInsuranceSystem.repository.AddressRepository;
import com.springboot.VehicleInsuranceSystem.repository.CustomerDocumentRepository;
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
	@Autowired
	private CustomerDocumentRepository customerDocumentRepository;
	
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
	

	public CustomerDocument addCustomerDocument(CustomerDocument ci) {
		 
		return customerDocumentRepository.save(ci);
	}
	
	
	public CustomerDocument uploadImage(int cid, MultipartFile file) throws IOException, ResourceNotFoundException {
	
		String location = "/Users/dk/Documents/AngularProjects/sample-app/src/assets/images";
		Path path = Path.of(location, file.getOriginalFilename()); 
		//System.out.println(path.toString());
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw e; 
		}
		
		Customer customer=null;
		try {
			customer = findCustomer(cid);
		} catch (ResourceNotFoundException e) {
			 throw e; 
		}
		
		CustomerDocument ci = new CustomerDocument();
		ci.setFileName(file.getOriginalFilename());
		ci.setPath(path.toString());
		ci.setCustomer(customer);
		
		return addCustomerDocument(ci);
	}
	
	
	public List<CustomerDocument> getAllImages() {
		// TODO Auto-generated method stub
		return customerDocumentRepository.findAll();
	}


}
