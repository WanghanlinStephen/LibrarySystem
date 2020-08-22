package com.library.system.repository;

import com.library.system.entity.Book;
import java.util.List;
import javax.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book,Integer> {
  @Query("SELECT b FROM Book b WHERE b.name=?1")
  List<Book> findByName(String name);
}
