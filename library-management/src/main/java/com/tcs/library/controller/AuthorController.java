package com.tcs.library.controller;


import com.tcs.library.entity.Author;
import com.tcs.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Author> getAllAuthors() {
        return authorService.fetchAllAuthors();
    }

    @GetMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Author getAuthorById(@PathVariable int id) {
        return authorService.fetchAuthorById(id);
    }

    @PostMapping(value = "/add/authors", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Author addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @PutMapping(value = "/update/authors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Author updateAuthor(@PathVariable int id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/delete/authors/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteAuthorById(@PathVariable int id) {
        authorService.removeAuthorById(id);
    }
}
