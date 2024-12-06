package com.springboot.VehicleInsuranceSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.VehicleInsuranceSystem.service.UserSecurityService;
/*
 * This class will bring JWT filter to Spring and integrate everything.
 * Along with this, this class will also point Spring to User table
 * */
@Configuration
public class SecurityConfig {

	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		 .csrf((csrf) -> csrf.disable())
		 .authorizeHttpRequests(authorize -> authorize
				 
				 //User APIs
				    .requestMatchers(HttpMethod.POST, "/api/token").permitAll()
				    .requestMatchers(HttpMethod.POST, "/auth/sign-up").permitAll()
				 	 .requestMatchers(HttpMethod.GET, "/auth/user").authenticated()
				 	
				 //Customer APIs
				 	.requestMatchers(HttpMethod.POST,"/customer/add").permitAll()
				 	.requestMatchers(HttpMethod.GET,"/customer/viewall").hasAuthority("ADMIN")
				 	.requestMatchers(HttpMethod.GET,"/customer/active").hasAnyAuthority("ADMIN","CUSTOMER")
				 	.requestMatchers(HttpMethod.GET,"/customer/proposedpolicies").permitAll()
				 	.requestMatchers(HttpMethod.GET,"/customer/details/byId").hasAnyAuthority("ADMIN","CUSTOMER")// A
				 	.requestMatchers(HttpMethod.POST,"/customer/update").hasAuthority("CUSTOMER")
				 	.requestMatchers(HttpMethod.GET,"/api/get/customer").hasAuthority("CUSTOMER")
				 	
				 	
				 	
				 	
				 //Executive APIs
				 	.requestMatchers(HttpMethod.POST,"/executive/add").hasAuthority("ADMIN")
				    .requestMatchers(HttpMethod.GET,"/executive/viewall").hasAuthority("ADMIN")
				    .requestMatchers(HttpMethod.GET,"/executive/details/byId").hasAnyAuthority("EXECUTIVE_INSURANCE","EXECUTIVE_INSPECTION","ADMIN")
				    .requestMatchers(HttpMethod.POST,"/executive/update").hasAnyAuthority("EXECUTIVE_INSURANCE","EXECUTIVE_INSPECTION")
				    .requestMatchers(HttpMethod.GET,"/api/get/executive").hasAnyAuthority("EXECUTIVE_INSURANCE","EXECUTIVE_INSPECTION")
				    
				  
				    
				 //Address APIs
				    .requestMatchers(HttpMethod.POST,"/address/add").hasAnyAuthority("CUSTOMER","EXECUTIVE")//A
				    
				 //Vehicle APIs
				    .requestMatchers(HttpMethod.POST,"/vehicle/add").hasAuthority("CUSTOMER")
				    .requestMatchers(HttpMethod.GET,"/vehicle/details/bycustomerId").hasAnyAuthority("CUSTOMER","EXECUTIVE_INSPECTION")
				    
				 //Policy APIs
				    .requestMatchers(HttpMethod.POST,"/policy/add").hasAuthority("EXECUTIVE_INSURANCE")
				    .requestMatchers(HttpMethod.GET,"/policy/all").hasAuthority("ADMIN")
				    .requestMatchers(HttpMethod.DELETE,"/policy/delete").hasAuthority("ADMIN")
				    .requestMatchers(HttpMethod.PUT,"/policy/update").hasAuthority("ADMIN")
				    .requestMatchers(HttpMethod.GET,"/policy/type/get").hasAuthority("ADMIN")
				    .requestMatchers(HttpMethod.GET,"/policy/category/get").hasAuthority("ADMIN")
				    .requestMatchers(HttpMethod.GET,"/policy/premium/get").hasAuthority("ADMIN")
				    .requestMatchers(HttpMethod.GET,"/policy/byStatus").hasAuthority("EXECUTIVE_INSURANCE")
				    .requestMatchers(HttpMethod.POST,"/policy/accept").hasAuthority("CUSTOMER")
				    .requestMatchers(HttpMethod.POST,"/policy/reject").hasAuthority("CUSTOMER")
				
				    
				    
				 //Purchase APIs
				    .requestMatchers(HttpMethod.POST,"/purchase/policy").hasAuthority("CUSTOMER")
				    .requestMatchers(HttpMethod.POST,"/purchase/policy/byCustomer").hasAuthority("CUSTOMER")
				   
				 //VehicleInspection APIs
				    .requestMatchers(HttpMethod.POST,"/vehicle-inspection/book").hasAuthority("CUSTOMER")
				    .requestMatchers(HttpMethod.GET,"/vehicle/vehicle-inspection/book").hasAnyAuthority("EXECUTIVE_INSURANCE","EXECUTIVE_INSPECTION")
				    .requestMatchers(HttpMethod.POST,"/vehicle-inspection/completed").hasAuthority("EXECUTIVE_INSPECTION")
				    .requestMatchers(HttpMethod.POST,"/inspection/changestatus").hasAuthority("EXECUTIVE_INSURANCE")
				
				    
				   
				    
				    
				
				    
				    
				    
				 	
				 	
				.anyRequest().permitAll()
			) 
			.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			
		   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		 
		return http.build();
	}
	
	
	@Bean
	PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userSecurityService);
		authenticationProvider.setPasswordEncoder(getEncoder());
		return authenticationProvider;
	}
}
/*
 * Spring needs a middleware to understand and decode jwt token 
 * 
 */