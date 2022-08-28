package com.example.bookstore.repository;

import com.example.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    public List<Book> findAllByTitle(String title);
    public Book findByIsbn(String isbn);
    public List<Book> findAllByAuthors(String author);
}
