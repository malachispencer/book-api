package io.poc.book_api.exception;

import io.poc.book_api.model.error.ErrorDetails;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static io.poc.book_api.model.error.ErrorType.ERR_INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
@Profile("global-exception-handling")
public class GlobalExceptionHandler {

    private static final String LOG_MESSAGE_TEMPLATE = "{} - {}";

    @ResponseBody
    @ExceptionHandler(BaseException.class)
    ErrorDetails handleBaseException(BaseException ex, HttpServletResponse response) {
        log.error(LOG_MESSAGE_TEMPLATE, ex.getErrorCode(), ex.getErrorDescription(), ex);
        response.setStatus(ex.getHttpStatus().value());
        return ErrorDetails.builder()
                .type(ex.getErrorCode())
                .title(ex.getErrorDescription())
                .detail(ex.getMessage())
                .status(ex.getHttpStatus().value())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorDetails handleUnexpectedException(Exception ex) {
        log.error(LOG_MESSAGE_TEMPLATE, ERR_INTERNAL_SERVER_ERROR.getCode(), ERR_INTERNAL_SERVER_ERROR.getDescription(), ex);
        return ErrorDetails.builder()
                .type(ERR_INTERNAL_SERVER_ERROR.getCode())
                .title(ERR_INTERNAL_SERVER_ERROR.getDescription())
                .detail(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }
}

