package com.sdp.project.model;

import org.springframework.data.annotation.Id;

public class SubCategory {

	@Id
	private String subCategoryName;
	
	private double price;

	public SubCategory(String subCategoryName, double price) {
		super();
		this.subCategoryName = subCategoryName;
		this.price = price;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
