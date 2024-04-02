package com.gabrielluciano.movieapp.controller;

import com.gabrielluciano.movieapp.model.Movie;
import com.gabrielluciano.movieapp.repository.MovieRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRepository repository;

    @BeforeEach
    void setUp() {
        BDDMockito.when(repository.findAll()).thenReturn(List.of(
                Movie.builder().id(1L).title("Movie 1").releaseYear(2000).rating(9.1).genre("Drama").build(),
                Movie.builder().id(1L).title("Movie 2").releaseYear(1990).rating(8.1).genre("Horror").build()
        ));

        BDDMockito.when(repository.findById(1L)).thenReturn(
                Optional.of(
                        Movie.builder().id(1L).title("Movie 1").releaseYear(2000).rating(9.1).genre("Drama").build()
                )
        );
    }

    @Test
    void findAll_ShouldReturnListOfMovies() throws Exception {
        mockMvc.perform(get("/api/movies"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().string(containsString("Movie 1")))
                .andExpect(content().string(containsString("Movie 2")));
    }

    @Test
    void findById_ShouldReturnMovie() throws Exception {
        mockMvc.perform(get("/api/movies/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith("{")))
                .andExpect(content().string(containsString("Movie 1")));
    }

}
