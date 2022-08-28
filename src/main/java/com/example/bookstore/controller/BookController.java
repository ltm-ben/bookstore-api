package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title) {
        return bookRepository.findAllByTitle(title);
    }

    @GetMapping("/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return bookRepository.findAllByAuthors(author);
    }

    @PostMapping
    public ResponseEntity createBook(@RequestBody Book book) throws URISyntaxException {
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.created(new URI("/books/" + savedBook.getIsbn())).body(savedBook);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity updateBook(@PathVariable String isbn, @RequestBody Book book) {
        Book currentBook = bookRepository.findByIsbn(isbn);
        currentBook.setTitle(book.getTitle());
        currentBook.setYear(book.getYear());
        currentBook.setPrice(book.getPrice());
        currentBook.setGenre(book.getGenre());
        currentBook.setAuthors(book.getAuthors());
        currentBook = bookRepository.save(currentBook);

        return  ResponseEntity.ok(currentBook);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity deleteBook(@PathVariable String isbn) {
        Book currentBook = bookRepository.findByIsbn(isbn);
        bookRepository.delete(currentBook);
        return ResponseEntity.ok().build();
    }
}
