package com.example.accessingmongodb.repo;

import com.example.accessingmongodb.entity.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author,String> {


}
