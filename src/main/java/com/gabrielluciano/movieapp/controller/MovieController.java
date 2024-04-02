package com.gabrielluciano.movieapp.controller;

import com.gabrielluciano.movieapp.model.Movie;
import com.gabrielluciano.movieapp.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository repository;

    @GetMapping
    public List<Movie> findAll() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Movie> findById(@PathVariable("id") Long id) {
        return this.repository.findById(id);
    }
}
