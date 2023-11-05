package com.tcs.author.service;


import com.tcs.author.dto.Book;
import com.tcs.author.model.Author;
import com.tcs.author.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
    public List<Author> fetchAllAuthors() {
        return authorRepository.findAll();
    }

    public Author fetchAuthorById(long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(long id, Author author) {
        Optional<Author> authorOpt = authorRepository.findById(id);
        if (authorOpt.isPresent()) {
            author.setId(authorOpt.get().getId());
            authorRepository.save(author);
        } else {
           // log.info("Author doesn't exist" + author.getFirst_name());
        }
        return author;
    }

    public void removeAuthorById(long id) {
        authorRepository.deleteById(id);
    }

    public List<Book> getAllBookAuthorById(Long authorId) {
        String baseUri = "http://localhost:8082/api/books/author/";
     List<Book> bookList = restTemplate.getForObject(baseUri + authorId, List.class);
        return bookList;
    }
}
