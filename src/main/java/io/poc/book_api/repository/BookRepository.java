package io.poc.book_api.repository;

import io.poc.book_api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {

    Optional<Book> findBookByBookId(String bookId);

    @Modifying
    @Transactional
    @Query("UPDATE Book SET price = :newPrice WHERE bookId = :bookId")
    int updateBookPrice(@Param("bookId") String bookId, @Param("newPrice") BigDecimal newPrice);
}
