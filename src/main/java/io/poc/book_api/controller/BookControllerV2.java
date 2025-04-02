package io.poc.book_api.controller;

import io.poc.book_api.exception.BookNotFoundException;
import io.poc.book_api.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v2/book")
public class BookControllerV2 {

    private final BookService bookService;

    /* Exception handling with a try-catch block */

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getBook(@PathVariable("bookId") String bookId) {
        try {
            return ResponseEntity.ok(bookService.getBookById(bookId));
        } catch (BookNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
