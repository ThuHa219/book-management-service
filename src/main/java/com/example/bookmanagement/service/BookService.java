package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.domain.User;
import com.example.bookmanagement.model.BookCreateDTO;
import com.example.bookmanagement.model.BookDTO;
import com.example.bookmanagement.model.BookPartialDTO;
import com.example.bookmanagement.repository.BookRepository;
import com.example.bookmanagement.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // stereotype annotation
public class BookService { // => spring bean

    // user => controller => service => repository

//    @PostConstruct
//    public void postConstruct() {
//        System.out.println("Trước khi bean sẵn sàng");
//    }

    private BookRepository bookRepository;

    private UserRepository userRepository;

    @Autowired
    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public BookDTO getById(int id) {
        Book book = bookRepository.getById(id);
        return new BookDTO(book.getId(), book.getUser().getId(), book.getName(), book.getDescription());
    }

    public BookDTO create(BookCreateDTO request) {
        User user = userRepository.findById(request.getUserId());
        Book book = bookRepository.save(new
                Book(request.getId(), request.getName(), request.getDescription(), user));
        return new BookDTO(book.getId(), book.getUser().getId(), book.getName(), book.getDescription());
    }

    public BookDTO update(int id, BookCreateDTO request) {
        User user = userRepository.findById(request.getUserId());
        Book book = bookRepository.save(new
                Book(id, request.getName(), request.getDescription(), user));
        return new BookDTO(book.getId(), book.getUser().getId(), book.getName(), book.getDescription());
    }

    /**
     *  chuyền vào gì thì ta update cái đó không thì giữ nguyên giá trị cũ.
     * @param id
     * @param dto
     * @return
     */
    public BookDTO updatePartial(int id, BookPartialDTO dto) {
        Book originalBook = bookRepository.findById(id); // lấy cái book từ database lên
        Book book = bookRepository.save(updateMapper(originalBook, dto)); // update vào db
        return new BookDTO(book.getId(), null,
                book.getName(), book.getDescription());
    }

    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    /**
     * Map data từ request vào original dto. Nếu request có field có value thì ta map value đấy vào
     * còn nếu ko ta dữ nguyên giá trị cũ
     * @param originalBook
     * @param dto
     * @return
     */
    public Book updateMapper(Book originalBook, BookPartialDTO dto) {
        Book bookUpdated = new Book();
        bookUpdated.setId(originalBook.getId());
        if(dto.getName() == null) { // cả cục optional null
            bookUpdated.setName(originalBook.getName());
            if(dto.getDescription().isPresent()) { // hay là id null
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
