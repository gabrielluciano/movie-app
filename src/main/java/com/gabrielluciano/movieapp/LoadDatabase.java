package com.gabrielluciano.movieapp;

import com.gabrielluciano.movieapp.model.Movie;
import com.gabrielluciano.movieapp.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(MovieRepository repository) {

        Movie movie1 = new Movie("Inception", 2010, 8.8, "Sci-Fi");
        Movie movie2 = new Movie("The Shawshank Redemption", 1994, 9.3, "Drama");

        return args -> {
            log.info("Pré-carregando: {}", repository.save(movie1));
            log.info("Pré-carregando: {}", repository.save(movie2));
        };
    }
}
