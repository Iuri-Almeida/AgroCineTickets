package br.com.itau.letscode.agrocinetickets.Movie.service;

import br.com.itau.letscode.agrocinetickets.Movie.exception.MovieNotFoundException;
import br.com.itau.letscode.agrocinetickets.Movie.model.Movie;
import br.com.itau.letscode.agrocinetickets.Movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public int rated(UUID id) {
        return this.findById(id).getRated();
    }

    public Movie insert(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie update(UUID id, Movie movie) {
        Movie dbMovie = this.findById(id);

        this.updateData(dbMovie, movie);

        return movieRepository.save(dbMovie);
    }

    public void deleteById(UUID id) {
        movieRepository.deleteById(id);
    }

    private void updateData(Movie dbMovie, Movie movie) {
        if (Optional.ofNullable(movie.getTitle()).isPresent()) {
            dbMovie.setTitle(movie.getTitle());
        }

        if (Optional.ofNullable(movie.getPlot()).isPresent()) {
            dbMovie.setPlot(movie.getPlot());
        }

        if (Optional.ofNullable(movie.getRuntime()).isPresent()) {
            dbMovie.setRuntime(movie.getRuntime());
        }

        if (Optional.ofNullable(movie.getYear()).isPresent()) {
            dbMovie.setYear(movie.getYear());
        }

        if (Optional.ofNullable(movie.getRated()).isPresent()) {
            dbMovie.setRated(movie.getRated());
        }
    }

}
