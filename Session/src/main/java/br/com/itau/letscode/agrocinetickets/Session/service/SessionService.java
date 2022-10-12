package br.com.itau.letscode.agrocinetickets.Session.service;

import br.com.itau.letscode.agrocinetickets.Session.client.MoviesClient;
import br.com.itau.letscode.agrocinetickets.Session.client.RoomsClient;
import br.com.itau.letscode.agrocinetickets.Session.exception.SessionNotFoundException;
import br.com.itau.letscode.agrocinetickets.Session.model.Session;
import br.com.itau.letscode.agrocinetickets.Session.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final RoomsClient roomsClient;
    private final MoviesClient moviesClient;

    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    public Session findById(UUID id) {
        return sessionRepository.findById(id).orElseThrow(() -> new SessionNotFoundException("Session with id = " + id + " not found."));
    }

    public void chooseSeat(Session session, int line, int column) {
        session = this.findById(session.getId());

        if (!this.isSeatOccupied(session.getRoomId(), line, column)) {
            this.occupySeat(session.getRoomId(), line, column);
        }
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
