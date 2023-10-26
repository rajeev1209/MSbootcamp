package com.tcs.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String language;
    private int authorId;
    private int genreId;

   /* @ManyToMany
    @JoinColumn(name = "authorId", referencedColumnName = "id")
    private List<Author> authors;

    @ManyToMany
    @JoinColumn(name = "genreId", referencedColumnName = "id")
    private List<Genre> genres;*/


}
