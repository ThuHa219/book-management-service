package com.example.bookmanagement.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private int id;
    @Column(name = "book_name")
    private String name;
    @Column(name = "description")
    private String description;

//    private int userId;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user; // 1 User - 1 Book

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    private boolean isDeleted; // default false



//    @ManyToMany
//    @JoinTable(name = "user_book",
//                joinColumns = @JoinColumn(referencedColumnName = "book_id"),
//                inverseJoinColumns = @JoinColumn(referencedColumnName = "user_id"))
//    private List<User> users;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book(int id, String name, String description, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
    }

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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user.toString() +
                '}';
    }
}
