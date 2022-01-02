package com.sdp.project.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("professional")
public class Professional {

	private String professionalId;
	
	@Id
	private String email;
	
	private String name;
	
	private String address;
	
	private String city;
	
	private int zip;
	
	private long contact;
	
	private String password;
	
	private String profilePic;
	
	private String experience;
	
	private boolean approved;

	public Professional() {
		super();
	}

	public Professional(String professionalId, String email, String name, String address, String city, int zip, long contact, String password, String profilePic, String experience, boolean approved) {
		super();
		this.professionalId = professionalId;
		this.email = email;
		this.name = name;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.contact = contact;
		this.password = hash(password);
		this.profilePic = profilePic;
		this.experience = experience;
		this.approved = approved;
	}

	public String getProfessionalId() {
		return professionalId;
	}

	public void setProfessionalId(String professionalId) {
		this.professionalId = professionalId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = hash(password);;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	public static String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String generateProfessionalId() {
		String id = "U2C";
		int num = (int) (Math.random()*(99999-10000+1)+10000);
		id += num;
		return id;
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
