package com.tcs.borrowing.controller;

import com.tcs.borrowing.model.BorrowingRecord;
import com.tcs.borrowing.service.BorrowingRecordService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BorrowingRecordController {
    @Autowired
    private BorrowingRecordService borrowerService;

    @GetMapping("/borrowers")
    @Operation(summary = "Retrieve all Borrowing Record", description = "If Borrowing Record exist then it will display all the Borrowing Record details else an empty list will be displayed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Borrowing Record Added")})
    public ResponseEntity<?> getAllBorrowers() {
        return borrowerService.fetchAllBorrowers();
    }

    @GetMapping("/borrowers/{id}")
    @Operation(summary = "Get a  Borrowing Record by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified  Borrowing Record details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No  Borrowing Record with specified Id found")})
    public ResponseEntity<?> getBorrowerById(@PathVariable Long id) {
        return borrowerService.fetchBorrowerById(id);
    }

    @PostMapping(value = "/add/borrowers", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add a new Borrowing Record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Borrowing Record added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add Borrowing Record")})
    public ResponseEntity<?> addBorrower(@RequestBody BorrowingRecord borrower) {
        return borrowerService.addBorrower(borrower);
    }

    @PutMapping(value = "/update/borrowers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update the Borrowing Record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Borrowing Record details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Borrowing Record details updating failed"),
            @ApiResponse(responseCode = "404", description = "No Borrowing Record with specified Id found")})
    public ResponseEntity<String> updateBorrower(@PathVariable Long id, @RequestBody BorrowingRecord borrower) {
        return borrowerService.updateBorrower(id, borrower);
    }

    @DeleteMapping("/delete/borrowers/{id}")
    @Operation(summary = "Delete the Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Book details deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Book delete operation failed"),
            @ApiResponse(responseCode = "404", description = "No Book with specified Id found")})
    public ResponseEntity<String> deleteBorrowerById(@PathVariable Long id) {
       return borrowerService.removeBorrowerById(id);
    }


    @PutMapping(value = "/update/borrowingDetails/{bookId}/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update the Borrowing Details of the Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Borrowing Details of the Book update successfully"),
            @ApiResponse(responseCode = "500", description = "Borrowing update or add operation failed")})
    public ResponseEntity<?>  updateBorrowingDetails(@PathVariable Long bookId, @PathVariable String userName) {
        return borrowerService.updateBorrowingDetails(bookId, userName);
    }

    @GetMapping("/borrowingRecords/userName/{user}")
    @Operation(summary = "Get a  Borrowing Record by UserName")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified  Borrowing Record details retrieved successfully BY  user Name"),
            @ApiResponse(responseCode = "404", description = "No  Borrowing Record with specified User found")})
    public ResponseEntity<?>  getAllBorrowingRecordsByUser(@PathVariable String user) {
        return borrowerService.fetchAllBorrowingRecordByUserName(user);
    }

}
