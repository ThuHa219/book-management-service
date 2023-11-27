package com.example.bookmanagement.repository;

import com.example.bookmanagement.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findById(int id);
    List<Book> findByName(String name);

    @Query("SELECT b FROM Book b WHERE b.name = :name and b.description = :description")
    List<Book> findByNameAndDescription(@Param("name") String name,
                                        @Param("description") String description);

    @Query("INSERT INTO Book(user_id) values(:user_id)")
    @Modifying
    Book create(@Param("user_id") Integer user_id);

}
