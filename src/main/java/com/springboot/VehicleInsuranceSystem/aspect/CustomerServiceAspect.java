package com.springboot.VehicleInsuranceSystem.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CustomerServiceAspect {
	Logger logger =  LoggerFactory.getLogger(CustomerServiceAspect.class);
	
	@Around("execution(* com.springboot.VehicleInsuranceSystem.service.CustomerService.* (..))")
	public Object recordCustomerExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		 long startTime = System.currentTimeMillis();
		Object object =  proceedingJoinPoint.proceed(); //this calls the method 
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		logger.info("Execution time of method " + proceedingJoinPoint.getSignature() + "was " + executionTime + "mills");
		return object;                                       
	}
	
	@Around("execution(* com.springboot.VehicleInsuranceSystem.service.ExecutiveService.* (..))")
	public Object recordExecutiveExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		 long startTime = System.currentTimeMillis();
		Object object =  proceedingJoinPoint.proceed(); //this calls the method 
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		logger.info("Execution time of method " + proceedingJoinPoint.getSignature() + "was " + executionTime + "mills");
		return object;                                       
	}
}