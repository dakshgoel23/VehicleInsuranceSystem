package com.springboot.VehicleInsuranceSystem.model;

import java.time.LocalDate;

import com.springboot.VehicleInsuranceSystem.enums.RiskLevel;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class RiskAssessment {
	
	//this table contains assessment details to help calculating premium amount
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private Vehicle vehicle; // The risk assessment is tied to a specific vehicle

    private LocalDate assessmentDate;

    private Double riskScore; // A score based on various risk factors

    private String assessmentDetails; // Detailed assessment info (e.g., accidents, history)

    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel; // LOW, MEDIUM, HIGH
    
    @ManyToOne
    private Executive executive;
    

	public Executive getExecutive() {
		return executive;
	}

	public void setExecutive(Executive executive) {
		this.executive = executive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public LocalDate getAssessmentDate() {
		return assessmentDate;
	}

	public void setAssessmentDate(LocalDate assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	public Double getRiskScore() {
		return riskScore;
	}

	public void setRiskScore(Double riskScore) {
		this.riskScore = riskScore;
	}

	public String getAssessmentDetails() {
		return assessmentDetails;
	}

	public void setAssessmentDetails(String assessmentDetails) {
		this.assessmentDetails = assessmentDetails;
	}

	public RiskLevel getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(RiskLevel riskLevel) {
		this.riskLevel = riskLevel;
	}
    
    
    
    

}
