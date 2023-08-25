package com.edstem.book.controller;

import com.edstem.book.contract.BookDto;
import com.edstem.book.model.Book;
import com.edstem.book.service.BookService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService)
    {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> findAllBooks() {
        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable int id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @GetMapping("/by-{author}")
    public ResponseEntity<List<BookDto>>getBooksByAuthor(@PathVariable String author) {
        return new ResponseEntity<>(bookService.getBooksByAuthor(author),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto book) {
        BookDto bookDto = bookService.addBook(book);
        return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBookById(
            @PathVariable int id, @Valid @RequestBody BookDto book) {
        BookDto updatedBook = bookService.updateBookById(id, book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable int id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book with ID " + id + " has been deleted");
    }
}
