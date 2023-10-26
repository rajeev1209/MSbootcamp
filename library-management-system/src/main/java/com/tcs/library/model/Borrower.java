package com.tcs.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Borrowers")
public class Borrower {
    @Id
    private int id;
    private String first_name;
    private String last_name;
    private Date dob;
    private String address;
    private int libId;


    /*@ManyToMany
    @JoinColumn(name = "libId", referencedColumnName = "id")
    private List<LibraryBranch> branches;*/
}
