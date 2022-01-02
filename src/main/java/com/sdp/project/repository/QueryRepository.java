package com.sdp.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sdp.project.model.UserQuery;

public interface QueryRepository extends MongoRepository<UserQuery, String>{

}
