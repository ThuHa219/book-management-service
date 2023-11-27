package com.example.bookmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

//UC1: 1 User - 1 Book

//UC2: 1 User - n Book

//UC3: n User - n Book

@Entity
@Table(name = "book_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

//    @Transient // ignore trên mặt db
//    @JsonIgnore // ignore trên web (postman)
    @OneToMany(mappedBy = "user")
    private List<Book> books;

//    @OneToOne(mappedBy = "user")
//    private Book book;

    public void setBook(List<Book> book) {
        this.books = book;
    }

    public List<Book> getBook() {
        return this.books;
    }

//    @ManyToMany(mappedBy = "users")
//    private List<Book> books;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", books=" + books +
                '}';
    }
}
