package com.tcs.library.service;


import com.tcs.library.entity.Author;
import com.tcs.library.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> fetchAllAuthors() {
        return authorRepository.findAll();
    }

    public Author fetchAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(int id, Author author) {
        Optional<Author> authorOpt = authorRepository.findById(id);
        if (authorOpt.isPresent()) {
            author.setId(authorOpt.get().getId());
            authorRepository.save(author);
        } else {
           // log.info("Author doesn't exist" + author.getFirst_name());
        }
        return author;
    }

    public void removeAuthorById(int id) {
        authorRepository.deleteById(id);
    }
}
