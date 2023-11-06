package com.tcs.book.controller;

import com.tcs.book.model.Book;
import com.tcs.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    @Operation(summary = "Retrieve all Books", description = "If Books exist then it will display all the Books details else an empty list will be displayed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Book Added")})
    public ResponseEntity<?> getAllBooks() {
        return bookService.fetchAllBooks();
    }

    @GetMapping("/books/{id}")
    @Operation(summary = "Get a Book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Book details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Book with specified Id found")})
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        return bookService.fetchBookById(id);
    }

    @PostMapping(value = "/add/books", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add a new Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add Book")})
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping(value = "/update/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update the Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Book details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Book details updating failed"),
            @ApiResponse(responseCode = "404", description = "No Book with specified Id found")})
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/delete/books/{id}")
    @Operation(summary = "Delete the Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Book details deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Book delete operation failed"),
            @ApiResponse(responseCode = "404", description = "No Book with specified Id found")})
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        return bookService.removeBookById(id);
    }

    @GetMapping("/books/author/{authorId}")
    @Operation(summary = "Retrieve all the Books written by an Author using author id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All the Books detail retrieved successfully written by an Author"),
            @ApiResponse(responseCode = "500", description = "Error while fetching books"),
            @ApiResponse(responseCode = "404", description = "No Book found for this author")})
    public ResponseEntity<?> getAllBooksByAuthor(@PathVariable Long authorId) {
        return bookService.fetchAllBooksByAuthor(authorId);
    }

    @PutMapping(value = "/update/books/borrowOrReturn/{id}/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "update Books status and BorrowingDetails in Borrowing Micro-service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status and Borrowing Detail update successfully of the book"),
            @ApiResponse(responseCode = "500", description = "Error while updating details"),
            @ApiResponse(responseCode = "404", description = "No Book found for this BookId")})
    public ResponseEntity<String> updateBookBorrowOrReturn(@PathVariable Long id, @RequestBody Book book, @PathVariable String userName) {
        return bookService.updateBookBorrowOrReturn(id, book, userName);
    }

}
