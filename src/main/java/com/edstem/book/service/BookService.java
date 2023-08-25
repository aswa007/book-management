package com.edstem.book.service;

import com.edstem.book.contract.BookDto;
import com.edstem.book.exception.AuthorNotFoundException;
import com.edstem.book.exception.BookNotFoundException;
import com.edstem.book.model.Book;
import com.edstem.book.repository.BookRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converters;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public List<BookDto> findAllBooks() {
        List<Book> books = this.bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    public BookDto getBookById(int id) {
        Book book =
                this.bookRepository
                        .findById(id)
                        .orElseThrow(
                                () -> {
                                    log.error("Book with id: {} not found", id);
                                    return new BookNotFoundException(id);
                                });
        return modelMapper.map(book, BookDto.class);
    }
    public List<BookDto> getBooksByAuthor(String author) {
        List<Book> booksByAuthor =this.bookRepository.findByAuthor(author);
        if (booksByAuthor.isEmpty()) {
            return Collections.emptyList();
           //return new AuthorNotFoundException(author);
        }
        return booksByAuthor.stream()
                .map(Book -> modelMapper.map(Book, BookDto.class))
                .collect(Collectors.toList());

    }

    public BookDto addBook(BookDto bookDto) {
        Book bookEntity = bookRepository.save(modelMapper.map(bookDto, Book.class));
        return modelMapper.map(bookEntity, BookDto.class);
    }

    public BookDto updateBookById(int id, BookDto book) {
        Book existingBook =
                bookRepository
                        .findById(id)
                        .orElseThrow(
                                () -> {
                                    log.error("Book with id: {} not found", id);
                                    return new BookNotFoundException(id);
                                });

        modelMapper.map(book, existingBook);
        Book updatedBook = bookRepository.save(existingBook);
        return modelMapper.map(updatedBook, BookDto.class);
    }

    public void deleteBookById(int id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }
}
