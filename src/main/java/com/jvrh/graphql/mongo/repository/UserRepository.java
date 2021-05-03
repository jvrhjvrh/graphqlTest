package com.jvrh.graphql.mongo.repository;

import com.jvrh.graphql.mongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {
    public Optional<User> findById(String id);
}
