package com.springboot.VehicleInsuranceSystem;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.Address;
import com.springboot.VehicleInsuranceSystem.repository.AddressRepository;
import com.springboot.VehicleInsuranceSystem.service.AddressService;

@SpringBootTest
public class AddressServiceTest {
	
	@InjectMocks
	private AddressService addressService;
	
	@Mock
	private AddressRepository addressRepository;
	
	private Address address;
	
	@BeforeEach
	public void initSetup()
	{
		address = new Address(3,"Vasant Vihar","Delhi","4587976");
		
		
	}
	
	@Test
	public void postCustomerTest()
	{
		       
				when(addressRepository.save(address)).thenReturn(address);
				
			
				Address newAddress =   addressService.insert(address); 
				
		
				assertNotNull(newAddress);
		
				verify(addressRepository, times(1)).save(address);
	}
	
	@Test
	public void getByIdTest() throws ResourceNotFoundException {
	
		when(addressRepository.findById(1)).thenReturn(Optional.of(address));
		

		Address newAddress = null;
			newAddress =  addressService.getById(1);

		

		assertNotNull(newAddress);
		verify(addressRepository, times(1)).findById(1);
	}
}
