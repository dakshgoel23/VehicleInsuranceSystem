package com.springboot.VehicleInsuranceSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.springboot.VehicleInsuranceSystem.enums.Role;
import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.Address;
import com.springboot.VehicleInsuranceSystem.model.Customer;
import com.springboot.VehicleInsuranceSystem.model.User;
import com.springboot.VehicleInsuranceSystem.repository.CustomerRepository;
import com.springboot.VehicleInsuranceSystem.service.CustomerService;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;
    private User user;
    private Address address;

    @BeforeEach
    public void setUp() {
    	
    	 MockitoAnnotations.openMocks(this); // error was coming that customerRepository is not initialized
    	 
    	user = new User(1,"janedoe@gmail.com","janedoe@123",Role.CUSTOMER,true);
        

        address = new Address(1,"Vasant Vihar","Delhi","3827829");
        
        customer = new Customer(1,"Jane Doe","99237294234","81928192839192",user,address);
    }

    @Test
    public void testInsertCustomer() {

        when(customerRepository.save(customer)).thenReturn(customer);

   
        Customer newCustomer = customerService.insert(customer);


        assertNotNull(newCustomer);
        assertEquals("Jane Doe", newCustomer.getName());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testViewAllCustomers() {
      
        List<Customer> customers = Arrays.asList(customer);
        when(customerRepository.findAll()).thenReturn(customers);

       
        List<Customer> result = customerService.viewall();


        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Jane Doe", result.get(0).getName());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testFindCustomerById_Success() throws ResourceNotFoundException {

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        
        Customer result = customerService.findCustomer(1);

 
        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        verify(customerRepository, times(1)).findById(1);
    }

    @Test
    public void testFindCustomerById_NotFound() {
    
        when(customerRepository.findById(1)).thenReturn(Optional.empty());


        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            customerService.findCustomer(1);
        });

        assertEquals("Invalid id invalid", exception.getMessage());
        verify(customerRepository, times(1)).findById(1);
    }
}