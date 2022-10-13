package br.com.itau.letscode.agrocinetickets.Ticket.service;

import br.com.itau.letscode.agrocinetickets.Ticket.client.MoviesClient;
import br.com.itau.letscode.agrocinetickets.Ticket.client.RoomsClient;
import br.com.itau.letscode.agrocinetickets.Ticket.client.SessionsClient;
import br.com.itau.letscode.agrocinetickets.Ticket.config.RabbitMQConfig;
import br.com.itau.letscode.agrocinetickets.Ticket.exception.SeatIsOccupiedException;
import br.com.itau.letscode.agrocinetickets.Ticket.exception.SessionNullPointerException;
import br.com.itau.letscode.agrocinetickets.Ticket.exception.TicketNotFoundException;
import br.com.itau.letscode.agrocinetickets.Ticket.model.Session;
import br.com.itau.letscode.agrocinetickets.Ticket.model.Ticket;
import br.com.itau.letscode.agrocinetickets.Ticket.model.enums.TicketStatus;
import br.com.itau.letscode.agrocinetickets.Ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final RoomsClient roomsClient;
    private final MoviesClient moviesClient;
    private final SessionsClient sessionsClient;
    private final RabbitTemplate template;

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket findById(UUID id) {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("Ticket with id = " + id + " not found."));
    }

    public Ticket commit(Session session, int line, int column) {
        session = this.findSession(session);

        if (!this.isSeatOccupied(session.getRoomId(), line, column)) {
            this.occupySeat(session.getRoomId(), line, column);

            Ticket ticket = new Ticket(null, session.getId(), TicketStatus.PENDING);

            template.convertAndSend(RabbitMQConfig.TICKETS_PAYMENTS_DIRECT_EXCHANGE, "", ticket);

            return ticketRepository.save(ticket);
        }

        throw new SeatIsOccupiedException("Seat with line = " + line + " and column = " + column + " is occupied.");
    }

    public void updateStatus(UUID id, TicketStatus status) {
        Ticket ticket = this.findById(id);

        ticket.setStatus(status);

        ticketRepository.save(ticket);
    }

    private Session findSession(Session session) {
        return Optional.ofNullable(sessionsClient.findById(session.getId()).getBody()).orElseThrow(() -> new SessionNullPointerException("The returned session is null."));
    }

    private int rated(UUID id) {
        return moviesClient.rated(id).getBody();
    }

    private boolean isSeatOccupied(UUID id, int line, int column) {
        return Boolean.TRUE.equals(roomsClient.isSeatOccupied(id, line, column).getBody());
    }

    private void occupySeat(UUID id, int line, int column) {
        roomsClient.occupySeat(id, line, column);
    }

}
