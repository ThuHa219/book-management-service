package com.example.bookmanagement.domain;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "book_name")
    private String name;
    @Column(name = "description")
    private String description;

    public Book(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Book() {
        // do nothing
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Book) {
            return this.id == ((Book) o).getId() && this.name.equals(((Book) o).getName())
                    && this.description.equals(((Book) o).getDescription());
        }
        return false;
    }
}
