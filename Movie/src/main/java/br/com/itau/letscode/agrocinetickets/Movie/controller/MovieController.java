package br.com.itau.letscode.agrocinetickets.Movie.controller;

import br.com.itau.letscode.agrocinetickets.Movie.model.Movie;
import br.com.itau.letscode.agrocinetickets.Movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value =  "/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok().body(movieService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(movieService.findById(id));
    }

}
