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
@Table(name = "LibraryBranches")
public class LibraryBranch {
    @Id
    private int id;
    private String libraryName;
    private String address;
    private int brrId;
    private int bookId;

    /*@ManyToMany
    @JoinColumn(name = "brrId", referencedColumnName = "id")
    private List<Borrower> borrowers;

    @ManyToMany
    @JoinColumn(name = "bookId", referencedColumnName = "id")
    private List<Book> books;*/
}
