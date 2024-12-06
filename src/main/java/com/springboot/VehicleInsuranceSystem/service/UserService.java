package com.springboot.VehicleInsuranceSystem.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.VehicleInsuranceSystem.enums.Role;
import com.springboot.VehicleInsuranceSystem.exception.InvalidUsernameException;
import com.springboot.VehicleInsuranceSystem.exception.ResourceNotFoundException;
import com.springboot.VehicleInsuranceSystem.model.User;
import com.springboot.VehicleInsuranceSystem.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	public User signUp(User user) throws InvalidUsernameException {
		//check for username duplicacy 
		Optional<User> optional = userRepository.findByUsername(user.getUsername());
		if(optional.isPresent()) {
			throw new InvalidUsernameException("Username already in use");
		}
		
		//encrypt the password 
		String encryptedPass = passEncoder.encode(user.getPassword());
		user.setPassword(encryptedPass);
		
		user.setRole(Role.CUSTOMER);
		
		
		return userRepository.save(user);
	}
	
	
       public User validate(int id) throws ResourceNotFoundException {
		
		Optional< User> optional=userRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Invalid id invalid");
		
		 User user = optional.get();
		return user;  
		
		
	}
}