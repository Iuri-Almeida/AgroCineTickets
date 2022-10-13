package br.com.itau.letscode.agrocinetickets.Session.exception.handler;

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
    public ResponseEntity<StandardError> movieNotFound(SessionNotFoundException e, HttpServletRequest request) {
        String error = "Session not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
