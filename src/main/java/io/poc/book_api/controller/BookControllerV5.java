package io.poc.book_api.controller;

import io.poc.book_api.model.Book;
import io.poc.book_api.model.UpdateBookPriceRequest;
import io.poc.book_api.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v5/book")
public class BookControllerV5 {

    private final BookService bookService;

    /* Exceptions handled by ExceptionHandlerController */

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable("bookId") String bookId) {
        return bookService.getBookById(bookId);
    }

    @PutMapping("/{bookId}/updatePrice")
    public ResponseEntity<Object> updateBookPrice(@PathVariable("bookId") String bookId,
                                                  @RequestBody UpdateBookPriceRequest updateBookPriceRequest
    ) {
            bookService.updateBookPrice(bookId, updateBookPriceRequest.getNewPrice());
            return ResponseEntity.noContent().build();
    }
}
