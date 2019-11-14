package com.example.accessingmongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "author")
public class Author {

    @Id
    private String id;
    private String name;
    private String surname;
    private LocalDate born;
    private LocalDate died;

    public Author() {
    }

    public Author(String name, String surname, LocalDate born, LocalDate died) {
        this.name = name;
        this.surname = surname;
        this.born = born;
        this.died = died;
    }

    public Author(String name, String surname, String born, String died) {
        this.name = name;
        this.surname = surname;
        this.born = LocalDate.parse(born);
        this.died = LocalDate.parse(died);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    public LocalDate getDied() {
        return died;
    }

    public void setDied(LocalDate died) {
        this.died = died;
    }

    public enum AuthorFields{
        NAME("name"),
        SURNAME("surname"),
        BORN("born"),
        DIED("died")
        ;

        private String value;

        AuthorFields(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
