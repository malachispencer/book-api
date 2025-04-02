package io.poc.book_api.model;

import lombok.Value;

@Value
public class UpdateBookPriceRequest {

    String newPrice;
}
