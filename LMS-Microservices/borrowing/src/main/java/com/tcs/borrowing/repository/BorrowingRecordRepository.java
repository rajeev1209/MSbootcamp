package com.tcs.borrowing.repository;

import com.tcs.borrowing.model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    List<BorrowingRecord> findByBookId(Long bookId);

    List<BorrowingRecord> findByUserName(String userName);
}
