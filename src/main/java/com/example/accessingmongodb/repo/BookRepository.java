package com.example.accessingmongodb.repo;

import com.example.accessingmongodb.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

    List<Book> findByTitleContainingOrderByTitle(String titleContains);

}