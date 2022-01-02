package com.sdp.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("services")
public class Services {

	@Id
	private String serviceName;
	
	private String serviceImageUrl;
	
	private List<Category> category;
	
	private List<String> professional;
	
	public Services() {
		super();
	}

	public Services(String serviceName, String serviceImageUrl, List<Category> category) {
		super();
		this.serviceName = serviceName;
		this.serviceImageUrl = serviceImageUrl;
		this.category = category;
		this.professional = new ArrayList<String>();
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceImageUrl() {
		return serviceImageUrl;
	}

	public void setServiceImageUrl(String serviceImageUrl) {
		this.serviceImageUrl = serviceImageUrl;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public List<String> getProfessional() {
		return professional;
	}

	public void setProfessional(List<String> professional) {
		this.professional = professional;
	}
	
	public String getExtension(String fileName) {
		StringTokenizer st = new StringTokenizer(fileName, ".");
		String extension = "";
		while (st.hasMoreTokens())
		{
			extension = st.nextToken();
		}
		extension = "." + extension;
		return extension;
	}
	
}
