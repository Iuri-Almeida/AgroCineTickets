package br.com.itau.letscode.agrocinetickets.Movie.exception.handler;

import br.com.itau.letscode.agrocinetickets.Movie.exception.MovieNotFoundException;
import br.com.itau.letscode.agrocinetickets.Movie.exception.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class MovieHandlerException {

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<StandardError> movieNotFound(MovieNotFoundException e, HttpServletRequest request) {
        String error = "Movie not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
