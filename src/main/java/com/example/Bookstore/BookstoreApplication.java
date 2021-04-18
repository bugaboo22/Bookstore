package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;
import com.example.Bookstore.domain.UserRepository;
import com.example.Bookstore.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner Bookdemo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository ) {
		return (args) -> {
			crepository.deleteAll();
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Horror"));
			crepository.save(new Category("WW2"));
			
			brepository.deleteAll();
			brepository.save(new Book("13423-312", "Miika Vähänen", "Miika ihmemaassa", "2020", crepository.findByName("Fantasy").get(0)));
			brepository.save(new Book("13423-313", "Pekka Pekkanen", "Pekka töpöhäntä", "2020", crepository.findByName("Horror").get(0)));
			brepository.save(new Book("13423-314", "Pauli Paulinen", "Paulin lihapullat", "2020", crepository.findByName("WW2").get(0)));
			
			
		};
	}
}