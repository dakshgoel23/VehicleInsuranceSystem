package com.springboot.VehicleInsuranceSystem.model;

import java.time.LocalDate;

import com.springboot.VehicleInsuranceSystem.enums.ClaimStatus;

//import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;



@Entity
public class Claim {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Policy policy;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private Executive executive;
	
	private double claim_amount;
	
	@Enumerated(EnumType.STRING)
	private ClaimStatus status;
	
	private LocalDate claim_date;
	
	private LocalDate claimStatusDate;



	public Executive getExecutive() {
		return executive;
	}

	public void setExecutive(Executive executive) {
		this.executive = executive;
	}

	public LocalDate getClaim_date() {
		return claim_date;
	}

	public void setClaim_date(LocalDate claim_date) {
		this.claim_date = claim_date;
	}

	public LocalDate getClaimStatusDate() {
		return claimStatusDate;
	}

	public void setClaimStatusDate(LocalDate claimStatusDate) {
		this.claimStatusDate = claimStatusDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getClaim_amount() {
		return claim_amount;
	}

	public void setClaim_amount(double claim_amount) {
		this.claim_amount = claim_amount;
	}

	public ClaimStatus getStatus() {
		return status;
	}

	public void setStatus(ClaimStatus status) {
		this.status = status;
	}
	
	

}
