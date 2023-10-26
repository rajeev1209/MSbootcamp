package com.tcs.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Genres")
public class Genre {
    @Id
    private int Id;
    private String type;
    private int bookId;

   /* @ManyToMany
    @JoinColumn(name = "bookId", referencedColumnName = "id")
    private List<Book> books;*/
}
