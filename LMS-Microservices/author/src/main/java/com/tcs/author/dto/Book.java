package com.tcs.author.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Book {

    private Long id;
    private String title;
    private String ISBN;
    private Long authorId; // Instead of the whole author object, we're storing authorId

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    enum BookStatus {
        AVAILABLE,
        BORROWED
    }
}
