package com.edstem.book.repository;

import com.edstem.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    boolean existsByIsbn(Long isbn);

    List<Book> findByAuthor(String author);
}
