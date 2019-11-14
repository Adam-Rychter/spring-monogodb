package com.example.accessingmongodb.entity;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorMapper {

    public List<Author> getAuthors(List<CSVRecord> records){

        List<Author> authors = new ArrayList<>();

        records.stream().forEach(record -> authors.add(
                new Author(record.get(Author.AuthorFields.NAME.getValue()),
                        record.get(Author.AuthorFields.SURNAME.getValue()),
                        record.get(Author.AuthorFields.BORN.getValue()),
                        record.get(Author.AuthorFields.DIED.getValue()))));

        return authors;
    }
}
