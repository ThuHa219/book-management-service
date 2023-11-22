package com.example.bookmanagement.repository;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.model.BookPartialDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository // stereotype annotation
public class BookRepository { // => spring bean
    private List<Book> books = new ArrayList<>();

    @PostConstruct
    public void init() {
        books.add(new Book(1, "A", "abc"));
        books.add(new Book(2, "B", "cde"));
        books.add(new Book(3, "C", "edf"));
    }


    public Book getById(int id) {
        for(Book book : this.books) {
            if(book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public Book create(Book book) {
        this.books.add(book);
        return book;
    }

    public Book update(int id, Book book) {
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getId() == id) {
                this.books.remove(i);
                this.books.add(book);
            }
        }
        return book;
    }

    public void delete(int id) {
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getId() == id) {
                this.books.remove(i);
            }
        }
    }
}
