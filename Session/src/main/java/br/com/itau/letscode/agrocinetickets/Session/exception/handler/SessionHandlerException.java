package br.com.itau.letscode.agrocinetickets.Session.exception.handler;

import br.com.itau.letscode.agrocinetickets.Session.exception.RoomResourceNullPointerException;
import br.com.itau.letscode.agrocinetickets.Session.exception.SeatOutOfBoundsException;
import br.com.itau.letscode.agrocinetickets.Session.exception.SessionNotFoundException;
import br.com.itau.letscode.agrocinetickets.Session.exception.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class SessionHandlerException {

    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<StandardError> sessionNotFound(SessionNotFoundException e, HttpServletRequest request) {
        String error = "Session not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(RoomResourceNullPointerException.class)
    public ResponseEntity<StandardError> roomResourceNullPointer(RoomResourceNullPointerException e, HttpServletRequest request) {
        String error = "Room resource is null";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(SeatOutOfBoundsException.class)
    public ResponseEntity<StandardError> seatOutOfBounds(SeatOutOfBoundsException e, HttpServletRequest request) {
        String error = "Seat out of bounds";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
