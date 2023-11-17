package com.example.bookmanagement.controller;

import com.example.bookmanagement.model.BookDTO;
import com.example.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller // stereotype annotation cho controller nói chung
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{bookId}")
    public BookDTO getById(@PathVariable("bookId") int id) {
        return bookService.getById(id);
    }

    @PostMapping
    public BookDTO create(@RequestBody BookDTO bookDTO) {
        return bookService.create(bookDTO);
    }

}
