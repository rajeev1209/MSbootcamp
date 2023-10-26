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
@Table(name = "Authors")
public class Author {

    @Id
    private int id;
    private String first_name;
    private String last_name;
    private Date dob;
    private String address;
    private int bookId;

    /*@ManyToMany
    @JoinColumn(name = "bookId", referencedColumnName = "id")
    private List<Book> books;*/

}
