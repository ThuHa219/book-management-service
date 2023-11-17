package com.example.bookmanagement.config;

import com.example.bookmanagement.repository.BookRepository;
import com.example.bookmanagement.test.TestClass;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConfig {

    @Bean
    public TestClass getTestClass(BookRepository bookRepository) {
        return new TestClass(bookRepository);
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

}
