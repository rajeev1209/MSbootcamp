package com.tcs.book.service;

import com.tcs.book.dto.BorrowingRecord;
import com.tcs.book.model.Book;
import com.tcs.book.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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

    public ResponseEntity<?> fetchAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        if (!CollectionUtils.isEmpty(bookList)) {
            return ResponseEntity.ok(bookList);
        }
        return new ResponseEntity<>("No Book Added", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> fetchBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return ResponseEntity.ok(bookOptional.get());
        }
        return new ResponseEntity<>("No author with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> addBook(Book book) {
        try {
            bookRepository.save(book);
            return new ResponseEntity<>("Book Details added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Exception raised while adding new Book " + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateBook(Long id, Book book) {
        try {
            Optional<Book> bookOpt = bookRepository.findById(id);
            if (bookOpt.isPresent()) {
                book.setId(bookOpt.get().getId());
                bookRepository.save(book);
                return new ResponseEntity<>("Book Details updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No book with specified Id found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Exception raised while updating book " + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> removeBookById(Long id) {
        try {
            Optional<Book> bookOpt = bookRepository.findById(id);
            if (bookOpt.isPresent()) {
                bookRepository.deleteById(id);
                return new ResponseEntity<>("Book Details deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Book Details Not Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Exception raised while deleting book " + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<?> fetchAllBooksByAuthor(Long authorId) {
        try {
            List<Book> bookList = bookRepository.findBooksByAuthorId(authorId);
            if (CollectionUtils.isEmpty(bookList)) {
                return new ResponseEntity<>("No book found with this author Id", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(bookList);
        } catch (Exception e) {
            return new ResponseEntity<>("Exception raised while fetching Books " + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<String> updateBookBorrowOrReturn(Long id, Book book, String userName) {
        try {
            Optional<Book> bookOpt = bookRepository.findById(id);
            if (bookOpt.isPresent()) {
                Book currBook = bookOpt.get();
                if (Book.BookStatus.BORROWED.equals(book.getStatus()) && Book.BookStatus.AVAILABLE.equals(currBook.getStatus())) {
                    book.setId(currBook.getId());
                    bookRepository.save(book);
                    updateBorrowingDetails(currBook.getId(), userName);
                    return new ResponseEntity<>("Book Details updated successfully", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Book- " + currBook.getTitle() + " can not be BORROWED, because it is not AVAILABLE", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("No book found with this author Id", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Exception raised while fetching Books " + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public BorrowingRecord updateBorrowingDetails(Long bookId, String userName) {
        return restTemplate.exchange("http://localhost:8083/api/update/borrowingDetails/" + bookId + "/" + userName, HttpMethod.PUT, null, BorrowingRecord.class).getBody();
    }
}
