package io.poc.book_api.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    ERR_VALIDATION_ERROR("BOOK-API-001", "The request was invalid"),
    ERR_INTERNAL_SERVER_ERROR("BOOK-API-002", "Internal server error"),
    ERR_BOOK_NOT_FOUND("BOOK-API-003", "Book not found");

    private final String code;
    private final String description;
}
