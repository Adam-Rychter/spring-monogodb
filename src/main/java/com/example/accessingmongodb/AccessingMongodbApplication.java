package com.example.accessingmongodb;

import com.example.accessingmongodb.entity.Author;
import com.example.accessingmongodb.entity.AuthorMapper;
import com.example.accessingmongodb.entity.Customer;
import com.example.accessingmongodb.repo.AuthorRepository;
import com.example.accessingmongodb.repo.BookRepository;
import com.example.accessingmongodb.repo.CustomerRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class AccessingMongodbApplication implements CommandLineRunner {

    private static Logger logger = Logger.getLogger(AccessingMongodbApplication.class);

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public static void main(String[] args) {
        SpringApplication.run(AccessingMongodbApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        testCustomer();
        insertAuthors();

    }

    private void insertAuthors() {

        authorRepository.deleteAll();
        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader(
                    Author.AuthorFields.NAME.getValue(),
                    Author.AuthorFields.SURNAME.getValue(),
                    Author.AuthorFields.BORN.getValue(),
                    Author.AuthorFields.DIED.getValue()
            ).withSkipHeaderRecord(true).parse(new InputStreamReader(new ClassPathResource("authors.csv")
                    .getInputStream()));

            authorRepository.saveAll(authorMapper.getAuthors(parser.getRecords()));

        } catch (FileNotFoundException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    private void testCustomer() {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }
    }
}
