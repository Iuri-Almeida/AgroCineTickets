package br.com.itau.letscode.agrocinetickets.Room.exception.handler;

import br.com.itau.letscode.agrocinetickets.Room.exception.RoomNotFoundException;
import br.com.itau.letscode.agrocinetickets.Room.exception.SeatOutOfBoundsException;
import br.com.itau.letscode.agrocinetickets.Room.exception.SessionNullPointerException;
import br.com.itau.letscode.agrocinetickets.Room.exception.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class RoomHandlerException {

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<StandardError> roomNotFound(RoomNotFoundException e, HttpServletRequest request) {
        String error = "Room not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
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

    @ExceptionHandler(SessionNullPointerException.class)
    public ResponseEntity<StandardError> sessionNullPointer(SessionNullPointerException e, HttpServletRequest request) {
        String error = "Session is null";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
