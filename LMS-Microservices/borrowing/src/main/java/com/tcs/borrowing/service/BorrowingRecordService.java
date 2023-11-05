package com.tcs.borrowing.service;

import com.tcs.borrowing.model.BorrowingRecord;
import com.tcs.borrowing.repository.BorrowingRecordRepository;
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

    public BorrowingRecord fetchBorrowerById(Long id) {
        return borrowerRepository.findById(id).orElse(null);
    }

    public BorrowingRecord addBorrower(BorrowingRecord borrower) {
        return borrowerRepository.save(borrower);
    }

    public BorrowingRecord updateBorrower(Long id, BorrowingRecord borrower) {
        Optional<BorrowingRecord> borrowerOpt = borrowerRepository.findById(id);
        if (borrowerOpt.isPresent()) {
            borrower.setId(borrowerOpt.get().getId());
            borrowerRepository.save(borrower);
        } else {
            //log.info("Borrower doesn't exist" + borrower.getFirst_name());
        }
        return borrower;
    }

    public void removeBorrowerById(Long id) {
        Optional<BorrowingRecord> borrowerOpt = borrowerRepository.findById(id);
        if (borrowerOpt.isPresent()) {
            borrowerRepository.deleteById(id);
        } else {
            log.info("Borrower doesn't exist so can't delete");
        }

    }
}
