package io.poc.book_api.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

    protected BaseException(String reason) {
        super(reason);
    }

    public abstract String getErrorCode();

    public abstract String getErrorDescription();

    public abstract HttpStatus getHttpStatus();
}
