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

import com.springboot.VehicleInsuranceSystem.enums.FuelType;
import com.springboot.VehicleInsuranceSystem.enums.Role;
import com.springboot.VehicleInsuranceSystem.enums.VehicleCategory;
import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.Address;
import com.springboot.VehicleInsuranceSystem.model.Customer;
import com.springboot.VehicleInsuranceSystem.model.User;
import com.springboot.VehicleInsuranceSystem.model.Vehicle;
import com.springboot.VehicleInsuranceSystem.repository.VehicleRepository;
import com.springboot.VehicleInsuranceSystem.service.VehicleService;

public class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    private Vehicle vehicle;
    private Customer customer;
    private User user;
    private Address address;

    @BeforeEach
    public void setUp() {
       
        MockitoAnnotations.openMocks(this);
        
         user = new User(1,"janedoe@gmail.com","janedoe@123",Role.CUSTOMER,true);
        

        address = new Address(1,"Vasant Vihar","Delhi","3827829");
        
        customer = new Customer(1,"Jane Doe","99237294234","81928192839192",user,address);
        
        
        vehicle = new Vehicle(1,"AB123CD","Toyota","Corolla",FuelType.PETROL,VehicleCategory.CAR,customer);
    }

    @Test
    public void testAddVehicle() {
 
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

     
        Vehicle newVehicle = vehicleService.addVehicle(vehicle);


        assertNotNull(newVehicle);
        assertEquals("AB123CD", newVehicle.getRegistration_number());
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    public void testGetVehicleDetails() {
    
        List<Vehicle> vehicles = Arrays.asList(vehicle);
        when(vehicleRepository.findbyCustomer(1)).thenReturn(vehicles);

      
        List<Vehicle> result = vehicleService.getVehicleDetails(1);

      
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("AB123CD", result.get(0).getRegistration_number());
        verify(vehicleRepository, times(1)).findbyCustomer(1);
    }

    @Test
    public void testValidateVehicle_Success() throws ResourceNotFoundException {
 
        when(vehicleRepository.findById(1)).thenReturn(Optional.of(vehicle));

    
        Vehicle result = vehicleService.validate(1);

    
        assertNotNull(result);
        assertEquals("AB123CD", result.getRegistration_number());
        verify(vehicleRepository, times(1)).findById(1);
    }

    @Test
    public void testValidateVehicle_NotFound() {
    
        when(vehicleRepository.findById(1)).thenReturn(Optional.empty());

   
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            vehicleService.validate(1);
        });

        assertEquals("Vehicle id invalid", exception.getMessage());
        verify(vehicleRepository, times(1)).findById(1);
    }
}