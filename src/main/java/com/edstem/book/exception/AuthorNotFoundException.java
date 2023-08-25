package com.edstem.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String author) {

        super("Could not find book with author: " + author);
    }
}
