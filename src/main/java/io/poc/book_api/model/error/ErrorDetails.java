package io.poc.book_api.model.error;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ErrorDetails {

    private final String type;
    private final String title;
    private final String detail;
    private final List<String> errors;
    private final int status;
}
