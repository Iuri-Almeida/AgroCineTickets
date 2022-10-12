package br.com.itau.letscode.agrocinetickets.Movie.exception;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String msg) {
        super(msg);
    }

}
