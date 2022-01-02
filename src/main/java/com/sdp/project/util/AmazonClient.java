package com.sdp.project.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@Service
public class AmazonClient 
{

    private AmazonS3 s3client;

    @Value("https://urban2cloud.s3-ap-south-1.amazonaws.com/")
    private String endpointUrl;
    
    @Value("urban2cloud")
    private String bucketName;
    
    @Value("AKIA56ZO6NLFRLEWB4PA")
    private String accessKey;
    
    @Value("UkJA4tkaQYRKsPg6GRqU7WzaX/6OBlzVZWp7/X4l")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
       AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
       this.s3client = new AmazonS3Client(credentials);
    }
    
    public String uploadFile(File f, String fileName)
    {
    	String fileUrl = endpointUrl + "professionals/" + fileName;
    	s3client.putObject(new PutObjectRequest(bucketName, "professionals/" + fileName, f).withCannedAcl(CannedAccessControlList.PublicRead));
    	return fileUrl;
    }
    
    public String uploadServiceImage(File f, String fileName)
    {
    	String fileUrl = endpointUrl + "Services/" + fileName;
    	s3client.putObject(new PutObjectRequest(bucketName, "Services/" + fileName, f).withCannedAcl(CannedAccessControlList.PublicRead));
    	return fileUrl;
    }
    
    public String getFile(String fileName) throws IOException
    {
    	S3Object object = s3client.getObject(new GetObjectRequest(bucketName, fileName));
    	BufferedReader reader = new BufferedReader(new InputStreamReader(object.getObjectContent()));
    	StringBuilder sb = new StringBuilder();
    	String s = null;
        while ((s = reader.readLine()) != null)
        {
            sb.append(s);
        }
        return sb.toString();
    }
    
}