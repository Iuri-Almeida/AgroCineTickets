package br.com.itau.letscode.agrocinetickets.Movie.controller;

import br.com.itau.letscode.agrocinetickets.Movie.model.Movie;
import br.com.itau.letscode.agrocinetickets.Movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/{id}/rated")
    public ResponseEntity<Integer> rated(@PathVariable UUID id) {
        return ResponseEntity.ok().body(movieService.rated(id));
    }

    @PostMapping
    public ResponseEntity<Movie> insert(@RequestBody Movie movie) {
        movie = movieService.insert(movie);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(movie.getId()).toUri();
        return ResponseEntity.created(uri).body(movie);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Movie> update(@PathVariable UUID id, @RequestBody Movie movie) {
        return ResponseEntity.ok().body(movieService.update(id, movie));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
