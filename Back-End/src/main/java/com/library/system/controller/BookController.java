package com.library.system.controller;

import com.library.system.entity.Book;
import com.library.system.repository.BookRepository;
import java.awt.print.Pageable;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
  @Autowired
  private BookRepository bookRepository;

  //Get
  @GetMapping("/findAll/{page}/{size}")
  public Page<Book> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
    PageRequest request = PageRequest.of(page,size);
    return bookRepository.findAll(request);
  }
  @GetMapping("/findById/{id}")
  public Book findById(@PathVariable("id") Integer id){
    return bookRepository.findById(id).get();
  }

  @GetMapping("/findByName/{name}")
  public List<Book> findByName(@PathVariable("name") String name){
    return bookRepository.findByName(name);
  }

  //Post
  @PostMapping("/save")
  public String save(@RequestBody Book book){
    Book result = bookRepository.save(book);
    if(result != null){
      return "success";
    }else{
      return "error";
    }
  }


  //Put
  @PutMapping("/update")
  public String update(@RequestBody Book book){
    Book result = bookRepository.save(book);
    if(result != null){
      return "success";
    }else{
      return "error";
    }
  }

  //Delete
  @DeleteMapping("/deleteById/{id}")
  public void deleteById(@PathVariable("id") Integer id){
    bookRepository.deleteById(id);
  }
}
