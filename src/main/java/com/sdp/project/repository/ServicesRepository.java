package com.sdp.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sdp.project.model.Services;

public interface ServicesRepository extends MongoRepository<Services, String> {

}
