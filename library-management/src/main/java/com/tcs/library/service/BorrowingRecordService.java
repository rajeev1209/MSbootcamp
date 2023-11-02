package com.tcs.library.service;

import com.tcs.library.entity.BorrowingRecord;
import com.tcs.library.repository.BorrowingRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowerRepository;

    public List<BorrowingRecord> fetchAllBorrowers() {
        return borrowerRepository.findAll();
    }

    public BorrowingRecord fetchBorrowerById(int id) {
        return borrowerRepository.findById(id).orElse(null);
    }

    public BorrowingRecord addBorrower(BorrowingRecord borrower) {
        return borrowerRepository.save(borrower);
    }

    public BorrowingRecord updateBorrower(int id, BorrowingRecord borrower) {
        Optional<BorrowingRecord> borrowerOpt = borrowerRepository.findById(id);
        if (borrowerOpt.isPresent()) {
            borrower.setId(borrowerOpt.get().getId());
            borrowerRepository.save(borrower);
        } else {
            //log.info("Borrower doesn't exist" + borrower.getFirst_name());
        }
        return borrower;
    }

    public void removeBorrowerById(int id) {
        Optional<BorrowingRecord> borrowerOpt = borrowerRepository.findById(id);
        if (borrowerOpt.isPresent()) {
            borrowerRepository.deleteById(id);
        } else {
            log.info("Borrower doesn't exist so can't delete");
        }

    }
}
