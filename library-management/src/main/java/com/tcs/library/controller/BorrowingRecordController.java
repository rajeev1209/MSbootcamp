package com.tcs.library.controller;


import com.tcs.library.entity.BorrowingRecord;
import com.tcs.library.service.BorrowingRecordService;
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
public class BorrowingRecordController {
    @Autowired
    private BorrowingRecordService borrowerService;

    @GetMapping("/borrowers")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BorrowingRecord> getAllBorrowers() {
        return borrowerService.fetchAllBorrowers();
    }

    @GetMapping("/borrowers/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BorrowingRecord getBorrowerById(@PathVariable int id) {
        return borrowerService.fetchBorrowerById(id);
    }

    @PostMapping(value = "/add/borrowers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BorrowingRecord addBorrower(@RequestBody BorrowingRecord borrower) {
        return borrowerService.addBorrower(borrower);
    }

    @PutMapping(value = "/update/borrowers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BorrowingRecord updateBorrower(@PathVariable int id, @RequestBody BorrowingRecord borrower) {
        return borrowerService.updateBorrower(id, borrower);
    }

    @DeleteMapping("/delete/borrowers/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteBorrowerById(@PathVariable int id) {
        borrowerService.removeBorrowerById(id);
    }
}
