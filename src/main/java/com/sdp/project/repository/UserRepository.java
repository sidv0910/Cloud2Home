package com.sdp.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sdp.project.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
}
