package com.tcs.borrowing.service;

import com.tcs.borrowing.model.BorrowingRecord;
import com.tcs.borrowing.repository.BorrowingRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowerRepository;

    public ResponseEntity<?> fetchAllBorrowers() {
        List<BorrowingRecord> borrowingRecordList = borrowerRepository.findAll();
        if (!CollectionUtils.isEmpty(borrowingRecordList)) {
            return ResponseEntity.ok(borrowingRecordList);
        }
        return new ResponseEntity<>("No Borrowing Record Added", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> fetchBorrowerById(Long id) {
        Optional<BorrowingRecord> borrowingRecordOptional = borrowerRepository.findById(id);
        if (borrowingRecordOptional.isPresent()) {
            ResponseEntity.ok(borrowingRecordOptional.get());
        }
        return new ResponseEntity<>("No author with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> addBorrower(BorrowingRecord borrower) {
        try {
            borrowerRepository.save(borrower);
            return new ResponseEntity<>("Borrowing Record Details added successfully", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Exception raised while adding new Borrowing Record " + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateBorrower(Long id, BorrowingRecord borrower) {
        try {
            Optional<BorrowingRecord> borrowerOpt = borrowerRepository.findById(id);
            if (borrowerOpt.isPresent()) {
                borrower.setId(borrowerOpt.get().getId());
                borrowerRepository.save(borrower);
                return new ResponseEntity<>("Borrowing Record Details updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Borrowing Record with specified Id found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Exception raised while updating Borrowing Record " + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> removeBorrowerById(Long id) {
        try {
            Optional<BorrowingRecord> borrowerOpt = borrowerRepository.findById(id);
            if (borrowerOpt.isPresent()) {
                borrowerRepository.deleteById(id);
                return new ResponseEntity<>("Borrowing Record Details deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Borrowing Record Details Not Found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>("Exception raised while deleting book " + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateBorrowingDetails(Long bookId, String userName) {
        try {
            List<BorrowingRecord> borrowingRecordList = borrowerRepository.findByBookId(bookId);
            if (CollectionUtils.isEmpty(borrowingRecordList)) {
                addBorrowingRecord(bookId, userName);
                return new ResponseEntity<>("Borrowing Record Details Added successfully", HttpStatus.OK);
            } else {
                Optional<BorrowingRecord> foundBorrowingRecord = borrowingRecordList.stream().filter(b -> b.getReturnDate() == null).findFirst();
                if (foundBorrowingRecord.isPresent()) {
                    BorrowingRecord bRecord = foundBorrowingRecord.get();
                    bRecord.setReturnDate(LocalDate.now());
                    borrowerRepository.save(bRecord);
                    return new ResponseEntity<>("Borrowing Record Details updated successfully", HttpStatus.OK);
                } else {
                    addBorrowingRecord(bookId, userName);
                    return new ResponseEntity<>("Borrowing Record Details Added successfully", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Exception raised while deleting book " + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private BorrowingRecord addBorrowingRecord(Long bookId, String userName) {
        BorrowingRecord borrowingRecord = BorrowingRecord.builder().bookId(bookId).borrowingDate(LocalDate.now()).userName(userName).build();
        borrowerRepository.save(borrowingRecord);
        return borrowingRecord;
    }

    public ResponseEntity<?> fetchAllBorrowingRecordByUserName(String userName) {
        List<BorrowingRecord> borrowingRecordList = borrowerRepository.findByUserName(userName);
        if (CollectionUtils.isEmpty(borrowingRecordList)) {
            return new ResponseEntity<>("Borrowing Record Details Not Found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(borrowingRecordList);
    }

}
