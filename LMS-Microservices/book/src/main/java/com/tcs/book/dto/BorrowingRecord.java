package com.tcs.book.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowingRecord {
    private Long id;
    private Long bookId;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private String userName;
}
