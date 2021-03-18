package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;
import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;
import com.example.Bookstore.web.BookController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Test
	public void findByTitle() {
		List<Book> books = brepository.findByTitle("Miika ihmemaassa");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Miika Vähänen");
	} 
	
	@Test
	public void findByName() {
		List<Category> category = crepository.findByName("WW2");
		assertThat(category.get(0).getName()).isEqualTo("WW2");
	}
	
	@Test
	public void deleteBook() {
		brepository.deleteAll();
	    assertThat(brepository.count()).isEqualTo(0);
	    assertThat(crepository.count()).isEqualTo(3);
	}
	
	@Test
	public void addBook() {
		Book book = new Book("Mikki", "Hiiri", "mh@mouse.com", "2020", crepository.findByName("Fantasy").get(0));
		brepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
}
