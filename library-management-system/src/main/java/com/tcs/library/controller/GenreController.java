package com.tcs.library.controller;

import com.tcs.library.model.Genre;
import com.tcs.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping("/genres")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Genre> getAllGenres() {
        return genreService.fetchAllGenres();
    }

    @GetMapping("/genres/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Genre getGenreById(@PathVariable int id) {
        return genreService.fetchGenreById(id);
    }

    @PostMapping(value = "/add/genres", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Genre addGenre(@RequestBody Genre genre) {
        return genreService.addGenre(genre);
    }

    @PutMapping(value = "/update/genres/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Genre updateGenre(@PathVariable int id, @RequestBody Genre genre) {
        return genreService.updateGenre(id, genre);
    }

    @DeleteMapping("/delete/genres/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteGenreById(@PathVariable int id) {
        genreService.removeGenreById(id);
    }
}
