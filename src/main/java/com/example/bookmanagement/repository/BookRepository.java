package com.example.bookmanagement.repository;

import com.example.bookmanagement.domain.Book;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository // stereotype annotation
public class BookRepository { // => spring bean
    private List<Book> books = new ArrayList<>(List.of(
         new Book(1, "A", "abc"),
         new Book(2, "B", "cde"),
         new Book(3, "C", "edf")
    ));

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
}
