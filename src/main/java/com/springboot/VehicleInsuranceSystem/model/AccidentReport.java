package com.springboot.VehicleInsuranceSystem.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class AccidentReport {
	//if policyHolder files a claim of policy this table will contain all details of accident
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;

	    @OneToOne
	    private Claim claim; // Each accident report is associated with a specific claim
	    
	    @ManyToOne
	    private Executive executive;
	    

		private String accidentLocation;
	    private String description;
	    
	    private LocalDate accidentDate;

	    private Double estimatedDamageAmount;

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

		public Claim getClaim() {
			return claim;
		}

		public void setClaim(Claim claim) {
			this.claim = claim;
		}

		public String getAccidentLocation() {
			return accidentLocation;
		}

		public void setAccidentLocation(String accidentLocation) {
			this.accidentLocation = accidentLocation;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public LocalDate getAccidentDate() {
			return accidentDate;
		}

		public void setAccidentDate(LocalDate accidentDate) {
			this.accidentDate = accidentDate;
		}

		public Double getEstimatedDamageAmount() {
			return estimatedDamageAmount;
		}

		public void setEstimatedDamageAmount(Double estimatedDamageAmount) {
			this.estimatedDamageAmount = estimatedDamageAmount;
		}
	    
	    

	
	
	

}
