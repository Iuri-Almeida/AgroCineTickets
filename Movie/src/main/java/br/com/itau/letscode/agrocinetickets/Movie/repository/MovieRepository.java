package br.com.itau.letscode.agrocinetickets.Movie.repository;

import br.com.itau.letscode.agrocinetickets.Movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
}
