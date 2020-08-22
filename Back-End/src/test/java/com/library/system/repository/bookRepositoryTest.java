package com.library.system.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class bookRepositoryTest {
  @Autowired
  private BookRepository bookRepository;

  @Test
  void findAll(){
    System.out.println(bookRepository.findAll());
  }


}