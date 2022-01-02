package com.sdp.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sdp.project.model.Professional;

public interface ProfessionalRepo extends MongoRepository<Professional, String>{

}
