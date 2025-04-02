package io.poc.book_api.exception;

import org.springframework.http.HttpStatus;

import static io.poc.book_api.model.error.ErrorType.ERR_BOOK_NOT_FOUND;
import static java.lang.String.format;

public class BookNotFoundException extends BaseException {

    public BookNotFoundException(String bookId) {
        super(format("Book with id %s not found", bookId));
    }

    @Override
    public String getErrorCode() {
        return ERR_BOOK_NOT_FOUND.getCode();
    }

    @Override
    public String getErrorDescription() {
        return ERR_BOOK_NOT_FOUND.getDescription();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
