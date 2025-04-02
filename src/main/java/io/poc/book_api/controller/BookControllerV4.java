package io.poc.book_api.controller;

import io.poc.book_api.exception.BookNotFoundException;
import io.poc.book_api.model.UpdateBookPriceRequest;
import io.poc.book_api.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v4/book")
public class BookControllerV4 {

    private final BookService bookService;

    /* Multiple endpoints with try-catch blocks */

    @GetMapping("/{bookId}")
    public ResponseEntity getBook(@PathVariable("bookId") String bookId) {
        try {
            return ResponseEntity.ok(bookService.getBookById(bookId));
        } catch (BookNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error occurred when trying to get book with Id {}", bookId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{bookId}/updatePrice")
    public ResponseEntity updateBookPrice(@PathVariable("bookId") String bookId, @RequestBody UpdateBookPriceRequest updateBookPriceRequest) {
        try {
            bookService.updateBookPrice(bookId, updateBookPriceRequest.getNewPrice());
            return ResponseEntity.noContent().build();
        } catch (BookNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error occurred when updating book price", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
