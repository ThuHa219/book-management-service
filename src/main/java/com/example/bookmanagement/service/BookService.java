package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.model.BookDTO;
import com.example.bookmanagement.repository.BookRepository;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service // stereotype annotation
public class BookService { // => spring bean

    // user => controller => service => repository

//    @PostConstruct
//    public void postConstruct() {
//        System.out.println("Trước khi bean sẵn sàng");
//    }

    private BookRepository bookRepository;

    private ObjectMapper objectMapper;

    @Autowired
    public BookService(BookRepository bookRepository, ObjectMapper objectMapper) {
        this.bookRepository = bookRepository;
        this.objectMapper = objectMapper;
    }

    public BookDTO getById(int id) {
        Book book = bookRepository.getById(id);
        return new BookDTO(book.getId(), book.getName(), book.getDescription());
    }

    public BookDTO create(BookDTO bookDTO) {
        Book book = bookRepository.create(new
                Book(bookDTO.getId(), bookDTO.getName(), bookDTO.getDescription()));
        return new BookDTO(book.getId(), book.getName(), book.getDescription());
    }

//    public BookRepository getBookRepository() {
//        return this.bookRepository;
//    }

//    public void print() {
//        System.out.println("BookService call");
//    }

//    @PreDestroy
//    public void preDestroy() {
//        System.out.println("Trước khi bean bị xoá");
//    }
}
