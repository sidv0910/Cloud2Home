package com.sdp.project.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Category {

	@Id
	private String categoryName;
	
	private List<SubCategory> subCategory;

	public Category(String categoryName, List<SubCategory> subCategory) {
		super();
		this.categoryName = categoryName;
		this.subCategory = subCategory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<SubCategory> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<SubCategory> subCategory) {
		this.subCategory = subCategory;
	}

}
