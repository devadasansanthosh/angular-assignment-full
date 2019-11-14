package com.stackroute.favouriteservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.favouriteservice.model.User;

public interface FavouriteRepository extends MongoRepository<User, String> {

	public User findByUsername(String username);
}
