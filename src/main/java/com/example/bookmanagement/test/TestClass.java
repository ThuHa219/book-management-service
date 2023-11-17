package com.example.bookmanagement.test;

import com.example.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class TestClass {

    private BookRepository bookRepository;

    public TestClass(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookRepository getBookRepository() {
        return this.bookRepository;
    }
}
