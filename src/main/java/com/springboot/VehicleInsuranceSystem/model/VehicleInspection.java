package com.springboot.VehicleInsuranceSystem.model;

import java.time.LocalDate;

import com.springboot.VehicleInsuranceSystem.enums.InspectionStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class VehicleInspection {
	//inspection will be done before issuing a policy to customer
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Vehicle vehicle; //inspection of which vehicle
    private LocalDate inspectionDate;
    
    private String vehicle_condition;
    
    private String driver_profile;
    
    private String want_coverage;
    
    @ManyToOne
    private Executive executive;
	
    private String inspectionReport; // Detailed report

    @Enumerated(EnumType.STRING)
    private InspectionStatus status; // BOOKED, COMPLETED
    

	public String getVehicle_condition() {
		return vehicle_condition;
	}

	public void setVehicle_condition(String vehicle_condition) {
		this.vehicle_condition = vehicle_condition;
	}

	public String getDriver_profile() {
		return driver_profile;
	}

	public void setDriver_profile(String driver_profile) {
		this.driver_profile = driver_profile;
	}

	public String getWant_coverage() {
		return want_coverage;
	}

	public void setWant_coverage(String want_coverage) {
		this.want_coverage = want_coverage;
	}

    
    public InspectionStatus getStatus() {
		return status;
	}

	public void setStatus(InspectionStatus status) {
		this.status = status;
	}
    
    

	public Executive getExecutive() {
		return executive;
	}

	public void setExecutive(Executive executive) {
		this.executive = executive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public LocalDate getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(LocalDate inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	public String getInspectionReport() {
		return inspectionReport;
	}

	public void setInspectionReport(String inspectionReport) {
		this.inspectionReport = inspectionReport;
	}

    
    
    

}
