package com.example.bookmanagement.controller;

import com.example.bookmanagement.model.BookCreateDTO;
import com.example.bookmanagement.model.BookDTO;
import com.example.bookmanagement.model.BookPartialDTO;
import com.example.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller // stereotype annotation cho controller nói chung
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{bookId}") // get by id: lấy book theo id
    public BookDTO getById(@PathVariable("bookId") int id) {
        return bookService.getById(id);
    }

    @PostMapping // create book
    public BookDTO create(@RequestBody BookCreateDTO bookCreateDTO) {
        return bookService.create(bookCreateDTO);
    }

    @PutMapping("{bookId}")
    public BookDTO update(@PathVariable("bookId") int id, @RequestBody BookCreateDTO bookCreateDTO) {
        return bookService.update(id, bookCreateDTO);
    }

    @PatchMapping("{bookId}")
    public BookDTO update(@PathVariable("bookId") int id, @RequestBody BookPartialDTO dto) {
        return bookService.updatePartial(id, dto);
    }

    @DeleteMapping("{bookId}")
    public String delete(@PathVariable("bookId") int id) {
        bookService.delete(id);
        return "success";
    }

//    @GetMapping
//    public List<BookDTO> getList() {
//
//    }
    // Viết API get list book

}
