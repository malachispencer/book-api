package io.poc.book_api.exception;

import static java.lang.String.format;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String bookId) {
        super(format("Book with Id %s not found", bookId));
    }
}
