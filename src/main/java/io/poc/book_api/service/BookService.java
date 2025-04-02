package io.poc.book_api.service;

import io.poc.book_api.model.Book;
import io.poc.book_api.exception.BookNotFoundException;
import io.poc.book_api.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book getBookById(String bookId) {
        return bookRepository.findBookByBookId(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
    }

    public void updateBookPrice(String bookId, String newPriceString) {
        int rowsUpdated = bookRepository.updateBookPrice(bookId, new BigDecimal(newPriceString));

        if (rowsUpdated == 0) {
            throw new BookNotFoundException(bookId);
        }
    }
}
