package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.model.BookDTO;
import com.example.bookmanagement.model.BookPartialDTO;
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

    public BookDTO update(int id, BookDTO bookDTO) {
        Book book = bookRepository.update(id, new
                Book(bookDTO.getId(), bookDTO.getName(), bookDTO.getDescription()));
        return new BookDTO(book.getId(), book.getName(), book.getDescription());
    }

    public BookDTO updatePartial(int id, BookPartialDTO dto) {
        Book originalBook = bookRepository.getById(id);
        Book book = bookRepository.update(id, updateMapper(originalBook, dto));
        return new BookDTO(book.getId(), book.getName(), book.getDescription());
    }

    public void delete(int id) {
        bookRepository.delete(id);
    }

    /**
     *
     * @param originalBook
     * @param dto
     * @return
     */
    public Book updateMapper(Book originalBook, BookPartialDTO dto) {
        Book bookUpdated = new Book();
        bookUpdated.setId(dto.getId().get());
        if(dto.getName() == null) {
            bookUpdated.setName(originalBook.getName());
            if(dto.getDescription().isPresent()) {
                bookUpdated.setDescription(dto.getDescription().get());
            } else {
                bookUpdated.setDescription(null);
            }
        }
        if(dto.getDescription() == null) {
            bookUpdated.setDescription(originalBook.getDescription());
            if(dto.getName().isPresent()) {
                bookUpdated.setName(dto.getName().get());
            } else {
                bookUpdated.setName(null);
            }
        }
        if(dto.getName() != null && dto.getDescription() != null) {
            if(dto.getName().isPresent()) {
                bookUpdated.setName(dto.getName().get());
            } else {
                bookUpdated.setName(null);
            }
            if(dto.getDescription().isPresent()) {
                bookUpdated.setDescription(dto.getDescription().get());
            } else {
                bookUpdated.setDescription(null);
            }
        }
        if(dto.getName() == null && dto.getDescription() == null) {
            bookUpdated.setName(originalBook.getName());
            bookUpdated.setDescription(originalBook.getDescription());
        }
        return bookUpdated;
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
