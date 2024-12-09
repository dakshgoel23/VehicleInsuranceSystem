package com.springboot.VehicleInsuranceSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.VehicleInsuranceSystem.model.CustomerDocument;

public interface CustomerDocumentRepository extends JpaRepository<CustomerDocument, Integer>{

}
