package com.wildcodeschool.librarymanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wildcodeschool.librarymanagement.entity.Book;
import com.wildcodeschool.librarymanagement.repository.BookRepository;


@RestController
public class BookController {

	@Autowired
    private BookRepository repository;

    @GetMapping("/books")
    public List<Book> index(){
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable long id){
        return repository.findById(id).get();
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("contains");
        return repository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return repository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable long id, @RequestBody Book book){
        // getting blog
    	Book blogToUpdate = repository.findById(id).get();
        blogToUpdate.setTitle(book.getTitle());
        blogToUpdate.setDescription(book.getDescription());
        return repository.save(blogToUpdate);
    }

    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable long id){
    	repository.deleteById(id);
        return true;
    }
}
