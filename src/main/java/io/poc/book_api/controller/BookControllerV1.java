package io.poc.book_api.controller;

import io.poc.book_api.model.Book;
import io.poc.book_api.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/book")
public class BookControllerV1 {

    private final BookService bookService;

    /* No exception handling */

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable("bookId") String bookId) {
        return bookService.getBookById(bookId);
    }
}
