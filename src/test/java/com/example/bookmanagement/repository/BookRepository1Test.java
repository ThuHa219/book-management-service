package com.example.bookmanagement.repository;

import com.example.bookmanagement.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BookRepository1Test {

    @Mock
    private BookRepository1 bookRepository1;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByIdWhenBookExistsThenReturnBook() {
        // Arrange
        Book book = new Book(1, "Test Book", "Test Description");
        given(bookRepository1.findById(1)).willReturn(book);

        // Act
        Book foundBook = bookRepository1.findById(1);

        // Assert
        assertEquals(book, foundBook);
    }

    @Test
    public void testFindByNameWhenBooksExistThenReturnBooks() {
        // Arrange
        Book book1 = new Book(1, "Test Book", "Test Description");
        Book book2 = new Book(2, "Test Book", "Test Description");
        List<Book> books = Arrays.asList(book1, book2);
        given(bookRepository1.findByName("Test Book")).willReturn(books);

        // Act
        List<Book> foundBooks = bookRepository1.findByName("Test Book");

        // Assert
        assertEquals(books, foundBooks);
    }
}