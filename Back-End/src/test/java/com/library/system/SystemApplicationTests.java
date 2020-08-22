package com.library.system;

import com.library.system.entity.Book;
import com.library.system.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SystemApplicationTests {
  @Autowired
  BookRepository bookRepository;
  @Test
  void contextLoads() {
  }
  @Test
  void save(){
    Book book= new Book();
    book.setAuthor("Stephen");
    book.setId(10);
    book.setName("W");
    Book data =bookRepository.save(book);
    System.out.println(data);
  }
  @Test
  void search(){
    System.out.println(bookRepository.findByName("q"));
  }


}
