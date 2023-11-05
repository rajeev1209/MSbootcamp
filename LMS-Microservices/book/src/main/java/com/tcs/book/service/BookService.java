package com.tcs.book.service;

import com.tcs.book.dto.Author;
import com.tcs.book.model.Book;
import com.tcs.book.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    public Author getAuthorById(Long authorId) {
        return restTemplate.getForObject("http://localhost:8081/authors/" + authorId, Author.class);
    }

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

    public List<Book> fetchAllBooksByAuthor(Long authorId) {
        return bookRepository.findBooksByAuthorId(authorId);
    }
}
