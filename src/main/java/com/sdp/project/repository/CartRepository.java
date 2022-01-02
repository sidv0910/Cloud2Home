package com.sdp.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sdp.project.model.Cart;

public interface CartRepository extends MongoRepository<Cart, String> {

}
