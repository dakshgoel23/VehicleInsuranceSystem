package com.springboot.VehicleInsuranceSystem.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.VehicleInsuranceSystem.model.CustomerDocument;

@Component
public class DocumentDto {
	
	private int id;
	
	private List<CustomerDocument> images;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CustomerDocument> getImages() {
		return images;
	}

	public void setImages(List<CustomerDocument> images) {
		this.images = images;
	}
	
	

}
