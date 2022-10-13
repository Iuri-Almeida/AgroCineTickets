package br.com.itau.letscode.agrocinetickets.Ticket.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String msg) {
        super(msg);
    }
}
