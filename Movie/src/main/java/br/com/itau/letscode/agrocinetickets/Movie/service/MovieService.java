package br.com.itau.letscode.agrocinetickets.Movie.service;

import br.com.itau.letscode.agrocinetickets.Movie.exception.MovieNotFoundException;
import br.com.itau.letscode.agrocinetickets.Movie.model.Movie;
import br.com.itau.letscode.agrocinetickets.Movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(UUID id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie with id = " + id + " not found."));
    }

}
