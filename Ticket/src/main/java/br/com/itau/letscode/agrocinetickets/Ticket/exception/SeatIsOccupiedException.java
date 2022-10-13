package br.com.itau.letscode.agrocinetickets.Ticket.exception;

public class SeatIsOccupiedException extends RuntimeException {
    public SeatIsOccupiedException(String msg) {
        super(msg);
    }
}
