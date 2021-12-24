package com.wildcodeschool.librarymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.librarymanagement.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findByTitleContainingOrDescriptionContaining(String text, String textAgain);
}
