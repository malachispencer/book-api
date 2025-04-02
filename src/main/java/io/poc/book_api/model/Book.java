package io.poc.book_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Book {

    @Id
    private String bookId;
    private String title;
    private String author;
    private int yearOriginallyPublished;
    private BigDecimal price;
}