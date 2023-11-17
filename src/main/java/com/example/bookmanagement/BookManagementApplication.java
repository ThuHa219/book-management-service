package com.example.bookmanagement;

import com.example.bookmanagement.repository.BookRepository;
import com.example.bookmanagement.service.BookService;
import com.example.bookmanagement.test.TestClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BookManagementApplication {

	public static void main(String[] args) {

//		System.out.println("Spring boot project started");
//
//		System.out.println("Sping IoC container started");
		// spring IoC container: quản lí các spring bean
		ApplicationContext context = SpringApplication.run(BookManagementApplication.class, args);

//		BookService service = context.getBean(BookService.class);
//		TestClass testClass = context.getBean(TestClass.class);
//		System.out.println(service.getBookRepository() == testClass.getBookRepository());

//		BookRepository repository = new BookRepository();// spring chỉ tạo ra 1 bean repo duy nhất
//		BookRepository repository1 = new BookRepository();
//		BookService service1 = new BookService(repository);
//		TestClass testClass1 = new TestClass(repository1);


	}

}
