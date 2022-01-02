package com.sdp.project.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("cart")
public class Cart {

	@Id
	private String userEmail;
	private String service;
	private LinkedHashMap<String, List<String>> category;
	private int cartCount;
	
	public Cart() {
		this.category = new LinkedHashMap<String, List<String>>();
		cartCount = 0;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public LinkedHashMap<String, List<String>> getCategory() {
		return category;
	}

	public void setCategory(LinkedHashMap<String, List<String>> category) {
		this.category = category;
	}

	public int getCartCount() {
		return cartCount;
	}

	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}
	
}
