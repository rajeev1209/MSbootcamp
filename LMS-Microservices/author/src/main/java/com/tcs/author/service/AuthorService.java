package com.tcs.author.service;


import com.tcs.author.dto.Book;
import com.tcs.author.model.Author;
import com.tcs.author.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> fetchAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (!CollectionUtils.isEmpty(authors)) {
            return ResponseEntity.ok(authors);
        }

        return new ResponseEntity<>("No Authors Added", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> fetchAuthorById(long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            return ResponseEntity.ok(authorOptional.get());
        }
        return new ResponseEntity<>("No author with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> addAuthor(Author author) {
        try {
            authorRepository.save(author);
            return new ResponseEntity<>("Author Details added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Exception raised while adding new author" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<String> updateAuthor(long id, Author author) {
        Optional<Author> authorDetails = authorRepository.findById(id);
        if (authorDetails.isPresent()) {
            try {
                author.setId(authorDetails.get().getId());
                authorRepository.save(author);
                return new ResponseEntity<>("Author Details updated successfully", HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("Exception raised while updating author" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No author with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> removeAuthorById(long id) {
        Optional<Author> authorDetails = authorRepository.findById(id);
        if (authorDetails.isPresent()) {
            try {
                authorRepository.deleteById(id);
                return new ResponseEntity<>("Author Details deleted successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Exception raised while deleting author" + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No author with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getAllBookAuthorById(Long authorId) {
        String baseUri = "http://localhost:8082/api/books/author/";
        try {
            List<Book> bookList = restTemplate.getForObject(baseUri + authorId, List.class);
            if (CollectionUtils.isEmpty(bookList)) {
                return new ResponseEntity<>("No book found with this author Id", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(bookList);
        } catch (Exception e) {
            return new ResponseEntity<>("Exception raised while fetching Books " + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
