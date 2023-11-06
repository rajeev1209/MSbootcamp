package com.tcs.author.controller;

import com.tcs.author.model.Author;
import com.tcs.author.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    @Operation(summary = "Retrieve all Authors", description = "If Authors exist then it will display all the author details else an empty list will be displayed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authors retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Authors Added")})
    public ResponseEntity getAllAuthors() {
        return authorService.fetchAllAuthors();
    }

    @GetMapping("/authors/{id}")
    @Operation(summary = "Get a Author by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Author details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Author with specified Id found")})
    public ResponseEntity<?> getAuthorById(@PathVariable int id) {
        return authorService.fetchAuthorById(id);
    }

    @PostMapping(value = "/add/authors", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add a new Author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add Author")})
    public ResponseEntity<?> addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @PutMapping(value = "/update/authors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update the Author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Author details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Author details updating failed"),
            @ApiResponse(responseCode = "404", description = "No Author with specified Id found")})
    public ResponseEntity<String> updateAuthor(@PathVariable int id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/delete/authors/{id}")
    @Operation(summary = "Delete the Author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Author details deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Author delete operation failed"),
            @ApiResponse(responseCode = "404", description = "No Author with specified Id found")})
    public ResponseEntity<String> deleteAuthorById(@PathVariable int id) {
        return authorService.removeAuthorById(id);
    }


    @GetMapping("/books/author/{authorId}")
    @Operation(summary = "Retrieve all the Books written by an Author using author id from Book Micro-service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All the Books detail retrieved successfully written by an Author"),
            @ApiResponse(responseCode = "500", description = "Error while fetching books"),
            @ApiResponse(responseCode = "404", description = "No Book found for this author")})
    public ResponseEntity<?> getAllBooksByAuthorId(@PathVariable Long authorId) {
        return authorService.getAllBookAuthorById(authorId);
    }

}