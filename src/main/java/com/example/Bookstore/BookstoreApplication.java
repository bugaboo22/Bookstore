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
			
			// Create users: admin/admin user/user
			User user1 = new User("user"
					,
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user", "user@gmail.com");
					User user2 = new User("admin"
					,
					"$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "admin@gmail.com");
					urepository.deleteAll();
					urepository.save(user1);
					urepository.save(user2);	
					
					log.info("fetch all books");
					for (Book book : brepository.findAll()) {
						log.info(book.toString());
					}
		};
	}
}