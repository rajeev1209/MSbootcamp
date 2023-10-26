package com.tcs.library.service;

import com.tcs.library.model.Genre;
import com.tcs.library.repository.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> fetchAllGenres() {
        return genreRepository.findAll();
    }

    public Genre fetchGenreById(int id) {
        return genreRepository.findById(id).orElse(null);
    }

    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre updateGenre(int id, Genre genre) {
        Optional<Genre> genreOpt = genreRepository.findById(id);
        if (genreOpt.isPresent()) {
            genre.setId(genreOpt.get().getId());
            genreRepository.save(genre);
        } else {
            log.info("Genre doesn't exist" + genre.getType());
        }
        return genre;
    }

    public void removeGenreById(int id) {
        genreRepository.deleteById(id);
    }
}
