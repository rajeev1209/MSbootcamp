package com.tcs.library.service;

import com.tcs.library.entity.Book;
import com.tcs.library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> fetchAllBooks() {
        return bookRepository.findAll();
    }

    public Book fetchBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(int id, Book book) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            book.setId(bookOpt.get().getId());
            bookRepository.save(book);
        } else {
            log.info("Book doesn't exist" + book.getTitle());
        }
        return book;
    }

    public void removeBookById(int id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            log.info("Book doesn't exist so can't delete");
        }

    }
}
