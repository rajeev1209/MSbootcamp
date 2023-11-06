package com.tcs.book.repository;


import com.tcs.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findBooksByAuthorId(Long authorId);
}
