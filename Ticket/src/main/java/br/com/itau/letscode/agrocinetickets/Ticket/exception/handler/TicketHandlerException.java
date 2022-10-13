package br.com.itau.letscode.agrocinetickets.Ticket.exception.handler;

import br.com.itau.letscode.agrocinetickets.Ticket.exception.SeatIsOccupiedException;
import br.com.itau.letscode.agrocinetickets.Ticket.exception.SessionNullPointerException;
import br.com.itau.letscode.agrocinetickets.Ticket.exception.StandardError;
import br.com.itau.letscode.agrocinetickets.Ticket.exception.TicketNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class TicketHandlerException {

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<StandardError> ticketNotFound(TicketNotFoundException e, HttpServletRequest request) {
        String error = "Ticket not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
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

    @ExceptionHandler(SeatIsOccupiedException.class)
    public ResponseEntity<StandardError> seatIsOccupied(SeatIsOccupiedException e, HttpServletRequest request) {
        String error = "Seat is occupied";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
