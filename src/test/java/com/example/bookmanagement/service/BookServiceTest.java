package com.example.bookmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.domain.User;
import com.example.bookmanagement.exception.DuplicateIdException;
import com.example.bookmanagement.exception.NotFoundException;
import com.example.bookmanagement.model.BookCreateDTO;
import com.example.bookmanagement.model.BookDTO;
import com.example.bookmanagement.model.BookPartialDTO;
import com.example.bookmanagement.model.FilterParam;
import com.example.bookmanagement.repository.BookRepository;
import com.example.bookmanagement.repository.BookRepositoryCustom;
import com.example.bookmanagement.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookService.class})
@ExtendWith(SpringExtension.class)
class BookServiceTest {
  @MockBean
  private BookRepository bookRepository;

  @MockBean
  private BookRepositoryCustom bookRepositoryCustom;

  @Autowired
  private BookService bookService;

  @MockBean
  private UserRepository userRepository;

  /**
   * Method under test: {@link BookService#getById(String)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testGetById() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   com.example.bookmanagement.exception.InvalidIdException: Id is invalid 42
    //       at com.example.bookmanagement.utils.UUIDUtils.validateUUID(UUIDUtils.java:13)
    //       at com.example.bookmanagement.service.BookService.getById(BookService.java:40)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    bookService.getById("42");
  }

  /**
   * Method under test: {@link BookService#create(BookCreateDTO)}
   */
  @Test
  void testCreate() {
    // Arrange
    User user = new User();
    user.setBooks(new ArrayList<>());
    user.setDeleted(true);
    user.setId("42");
    user.setName("Name");

    Book book = new Book();
    book.setAuthor("JaneDoe");
    book.setDeleted(true);
    book.setDescription("The characteristics of someone or something");
    book.setId("42");
    book.setName("Name");
    book.setUser(user);
    Optional<Book> ofResult = Optional.of(book);
    when(bookRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(DuplicateIdException.class, () -> bookService
            .create(new BookCreateDTO("42", "Name", "The characteristics of someone or something", "JaneDoe")));
    verify(bookRepository).findById(Mockito.<String>any());
  }

  /**
   * Method under test: {@link BookService#create(BookCreateDTO)}
   */
  @Test
  void testCreate2() {
    // Arrange
    when(bookRepository.findById(Mockito.<String>any())).thenThrow(new DuplicateIdException("Msg"));

    // Act and Assert
    assertThrows(DuplicateIdException.class, () -> bookService
            .create(new BookCreateDTO("42", "Name", "The characteristics of someone or something", "JaneDoe")));
    verify(bookRepository).findById(Mockito.<String>any());
  }

  /**
   * Method under test: {@link BookService#update(String, BookDTO)}
   */
  @Test
  void testUpdate() {
    // Arrange
    Optional<Book> emptyResult = Optional.empty();
    when(bookRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

    // Act and Assert
    assertThrows(NotFoundException.class,
            () -> bookService.update("42", new BookDTO("42", "Name", "The characteristics of someone or something")));
    verify(bookRepository).findById(Mockito.<String>any());
  }

  /**
   * Method under test: {@link BookService#update(String, BookDTO)}
   */
  @Test
  void testUpdate2() {
    // Arrange
    User user = new User();
    user.setBooks(new ArrayList<>());
    user.setDeleted(true);
    user.setId("42");
    user.setName("Name");

    Book book = new Book();
    book.setAuthor("JaneDoe");
    book.setDeleted(true);
    book.setDescription("The characteristics of someone or something");
    book.setId("42");
    book.setName("Name");
    book.setUser(user);
    Optional<Book> ofResult = Optional.of(book);
    when(bookRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
    Optional<User> emptyResult = Optional.empty();
    when(userRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

    // Act and Assert
    assertThrows(NotFoundException.class, () -> bookService.update("42",
            new BookDTO("42", "42", "Name", "The characteristics of someone or something", "JaneDoe")));
    verify(bookRepository).findById(Mockito.<String>any());
    verify(userRepository).findById(Mockito.<String>any());
  }

  /**
   * Method under test: {@link BookService#update(String, BookDTO)}
   */
  @Test
  void testUpdate3() {
    // Arrange
    User user = new User();
    user.setBooks(new ArrayList<>());
    user.setDeleted(true);
    user.setId("42");
    user.setName("Name");

    Book book = new Book();
    book.setAuthor("JaneDoe");
    book.setDeleted(true);
    book.setDescription("The characteristics of someone or something");
    book.setId("42");
    book.setName("Name");
    book.setUser(user);
    Optional<Book> ofResult = Optional.of(book);
    when(bookRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
    when(userRepository.findById(Mockito.<String>any())).thenThrow(new DuplicateIdException("Msg"));

    // Act and Assert
    assertThrows(DuplicateIdException.class, () -> bookService.update("42",
            new BookDTO("42", "42", "Name", "The characteristics of someone or something", "JaneDoe")));
    verify(bookRepository).findById(Mockito.<String>any());
    verify(userRepository).findById(Mockito.<String>any());
  }

  /**
   * Method under test: {@link BookService#updatePartial(String, BookPartialDTO)}
   */
  @Test
  void testUpdatePartial() {
    // Arrange
    User user = new User();
    user.setBooks(new ArrayList<>());
    user.setDeleted(true);
    user.setId("42");
    user.setName("Name");

    Book book = new Book();
    book.setAuthor("JaneDoe");
    book.setDeleted(true);
    book.setDescription("The characteristics of someone or something");
    book.setId("42");
    book.setName("Name");
    book.setUser(user);
    Optional<Book> ofResult = Optional.of(book);

    User user2 = new User();
    user2.setBooks(new ArrayList<>());
    user2.setDeleted(true);
    user2.setId("42");
    user2.setName("Name");

    Book book2 = new Book();
    book2.setAuthor("JaneDoe");
    book2.setDeleted(true);
    book2.setDescription("The characteristics of someone or something");
    book2.setId("42");
    book2.setName("Name");
    book2.setUser(user2);
    when(bookRepository.save(Mockito.<Book>any())).thenReturn(book2);
    when(bookRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

    User user3 = new User();
    user3.setBooks(new ArrayList<>());
    user3.setDeleted(true);
    user3.setId("42");
    user3.setName("Name");
    Optional<User> ofResult2 = Optional.of(user3);
    when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

    BookPartialDTO dto = new BookPartialDTO();
    dto.setAuthor("JaneDoe");
    dto.setDescription("The characteristics of someone or something");
    dto.setId("42");
    dto.setName("Name");
    dto.setUserId("42");

    // Act
    BookDTO actualUpdatePartialResult = bookService.updatePartial("42", dto);

    // Assert
    verify(bookRepository).findById(Mockito.<String>any());
    verify(userRepository).findById(Mockito.<String>any());
    verify(bookRepository).save(Mockito.<Book>any());
    assertEquals("42", actualUpdatePartialResult.getId());
    assertEquals("42", actualUpdatePartialResult.getUserId());
    assertEquals("JaneDoe", actualUpdatePartialResult.getAuthor());
    assertEquals("Name", actualUpdatePartialResult.getName());
    assertEquals("The characteristics of someone or something", actualUpdatePartialResult.getDescription());
    Optional<String> expectedId = dto.getUserId();
    assertEquals(expectedId, dto.getId());
  }

  /**
   * Method under test: {@link BookService#updatePartial(String, BookPartialDTO)}
   */
  @Test
  void testUpdatePartial2() {
    // Arrange
    User user = new User();
    user.setBooks(new ArrayList<>());
    user.setDeleted(true);
    user.setId("42");
    user.setName("Name");

    Book book = new Book();
    book.setAuthor("JaneDoe");
    book.setDeleted(true);
    book.setDescription("The characteristics of someone or something");
    book.setId("42");
    book.setName("Name");
    book.setUser(user);
    Optional<Book> ofResult = Optional.of(book);
    when(bookRepository.save(Mockito.<Book>any())).thenThrow(new DuplicateIdException("Msg"));
    when(bookRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

    User user2 = new User();
    user2.setBooks(new ArrayList<>());
    user2.setDeleted(true);
    user2.setId("42");
    user2.setName("Name");
    Optional<User> ofResult2 = Optional.of(user2);
    when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

    BookPartialDTO dto = new BookPartialDTO();
    dto.setAuthor("JaneDoe");
    dto.setDescription("The characteristics of someone or something");
    dto.setId("42");
    dto.setName("Name");
    dto.setUserId("42");

    // Act and Assert
    assertThrows(DuplicateIdException.class, () -> bookService.updatePartial("42", dto));
    verify(bookRepository).findById(Mockito.<String>any());
    verify(userRepository).findById(Mockito.<String>any());
    verify(bookRepository).save(Mockito.<Book>any());
  }

  /**
   * Method under test: {@link BookService#updatePartial(String, BookPartialDTO)}
   */
  @Test
  void testUpdatePartial3() {
    // Arrange
    Optional<Book> emptyResult = Optional.empty();
    when(bookRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

    User user = new User();
    user.setBooks(new ArrayList<>());
    user.setDeleted(true);
    user.setId("42");
    user.setName("Name");
    Book book = mock(Book.class);
    doNothing().when(book).setAuthor(Mockito.<String>any());
    doNothing().when(book).setDeleted(anyBoolean());
    doNothing().when(book).setDescription(Mockito.<String>any());
    doNothing().when(book).setId(Mockito.<String>any());
    doNothing().when(book).setName(Mockito.<String>any());
    doNothing().when(book).setUser(Mockito.<User>any());
    book.setAuthor("JaneDoe");
    book.setDeleted(true);
    book.setDescription("The characteristics of someone or something");
    book.setId("42");
    book.setName("Name");
    book.setUser(user);

    BookPartialDTO dto = new BookPartialDTO();
    dto.setAuthor("JaneDoe");
    dto.setDescription("The characteristics of someone or something");
    dto.setId("42");
    dto.setName("Name");
    dto.setUserId("42");

    // Act and Assert
    assertThrows(NotFoundException.class, () -> bookService.updatePartial("42", dto));
    verify(book).setAuthor(Mockito.<String>any());
    verify(book).setDeleted(anyBoolean());
    verify(book).setDescription(Mockito.<String>any());
    verify(book).setId(Mockito.<String>any());
    verify(book).setName(Mockito.<String>any());
    verify(book).setUser(Mockito.<User>any());
    verify(bookRepository).findById(Mockito.<String>any());
  }

  /**
   * Method under test: {@link BookService#updatePartial(String, BookPartialDTO)}
   */
  @Test
  void testUpdatePartial4() {
    // Arrange
    User user = new User();
    user.setBooks(new ArrayList<>());
    user.setDeleted(true);
    user.setId("42");
    user.setName("Name");

    Book book = new Book();
    book.setAuthor("JaneDoe");
    book.setDeleted(true);
    book.setDescription("The characteristics of someone or something");
    book.setId("42");
    book.setName("Name");
    book.setUser(user);
    Optional<Book> ofResult = Optional.of(book);
    when(bookRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

    User user2 = new User();
    user2.setBooks(new ArrayList<>());
    user2.setDeleted(true);
    user2.setId("42");
    user2.setName("Name");
    Book book2 = mock(Book.class);
    doNothing().when(book2).setAuthor(Mockito.<String>any());
    doNothing().when(book2).setDeleted(anyBoolean());
    doNothing().when(book2).setDescription(Mockito.<String>any());
    doNothing().when(book2).setId(Mockito.<String>any());
    doNothing().when(book2).setName(Mockito.<String>any());
    doNothing().when(book2).setUser(Mockito.<User>any());
    book2.setAuthor("JaneDoe");
    book2.setDeleted(true);
    book2.setDescription("The characteristics of someone or something");
    book2.setId("42");
    book2.setName("Name");
    book2.setUser(user2);
    Optional<User> emptyResult = Optional.empty();
    when(userRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

    BookPartialDTO dto = new BookPartialDTO();
    dto.setAuthor("JaneDoe");
    dto.setDescription("The characteristics of someone or something");
    dto.setId("42");
    dto.setName("Name");
    dto.setUserId("42");

    // Act and Assert
    assertThrows(NotFoundException.class, () -> bookService.updatePartial("42", dto));
    verify(book2).setAuthor(Mockito.<String>any());
    verify(book2).setDeleted(anyBoolean());
    verify(book2).setDescription(Mockito.<String>any());
    verify(book2).setId(Mockito.<String>any());
    verify(book2).setName(Mockito.<String>any());
    verify(book2).setUser(Mockito.<User>any());
    verify(bookRepository).findById(Mockito.<String>any());
    verify(userRepository).findById(Mockito.<String>any());
  }

  /**
   * Method under test: {@link BookService#delete(String)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testDelete() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   com.example.bookmanagement.exception.InvalidIdException: Id is invalid 42
    //       at com.example.bookmanagement.utils.UUIDUtils.validateUUID(UUIDUtils.java:13)
    //       at com.example.bookmanagement.service.BookService.delete(BookService.java:91)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    bookService.delete("42");
  }

  /**
   * Method under test: {@link BookService#getAll(FilterParam)}
   */
  @Test
  void testGetAll() {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.IllegalStateException: No current ServletRequestAttributes
    //       at com.example.bookmanagement.service.BookService.getAll(BookService.java:102)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange
    BookRepository bookRepository = mock(BookRepository.class);
    when(bookRepository.getTotalData()).thenThrow(new DuplicateIdException("Msg"));
    BookService bookService = new BookService(bookRepository, mock(UserRepository.class),
            mock(BookRepositoryCustom.class));

    // Act and Assert
    assertThrows(DuplicateIdException.class, () -> bookService.getAll(new FilterParam()));
    verify(bookRepository).getTotalData();
  }

  /**
   * Method under test: {@link BookService#borrowBook(String, String)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testBorrowBook() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   com.example.bookmanagement.exception.InvalidIdException: Id is invalid 42
    //       at com.example.bookmanagement.utils.UUIDUtils.validateUUID(UUIDUtils.java:13)
    //       at com.example.bookmanagement.service.BookService.borrowBook(BookService.java:126)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    bookService.borrowBook("42", "42");
  }
}
