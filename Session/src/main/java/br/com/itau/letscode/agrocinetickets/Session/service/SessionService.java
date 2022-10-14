package br.com.itau.letscode.agrocinetickets.Session.service;

import br.com.itau.letscode.agrocinetickets.Session.client.RoomClient;
import br.com.itau.letscode.agrocinetickets.Session.exception.RoomResourceNullPointerException;
import br.com.itau.letscode.agrocinetickets.Session.exception.SessionNotFoundException;
import br.com.itau.letscode.agrocinetickets.Session.model.Session;
import br.com.itau.letscode.agrocinetickets.Session.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final RoomClient roomClient;

    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    public Session findById(UUID id) {
        return sessionRepository.findById(id).orElseThrow(() -> new SessionNotFoundException("Session with id = " + id + " not found."));
    }

    public Session insert(Session session) {
        int lines = Optional.ofNullable(roomClient.getLines(session.getRoomId()).getBody()).orElseThrow(() -> new RoomResourceNullPointerException("Lines value returned is null."));
        int columns = Optional.ofNullable(roomClient.getColumns(session.getRoomId()).getBody()).orElseThrow(() -> new RoomResourceNullPointerException("Columns value returned is null."));

        return sessionRepository.save(new Session(null, session.getMovieID(), session.getRoomId(), session.getStartTime(), session.getEndTime(), lines, columns));
    }

    public Session update(UUID id, Session session) {
        Session dbSession = this.findById(id);

        this.updateData(dbSession, session);

        return sessionRepository.save(dbSession);
    }

    public void deleteById(UUID id) {
        sessionRepository.deleteById(id);
    }

    private void updateData(Session dbSession, Session session) {
        if (Optional.ofNullable(session.getMovieID()).isPresent()) {
            dbSession.setMovieID(session.getMovieID());
        }

        if (Optional.ofNullable(session.getRoomId()).isPresent()) {
            dbSession.setRoomId(session.getRoomId());
        }

        if (Optional.ofNullable(session.getStartTime()).isPresent()) {
            dbSession.setStartTime(session.getStartTime());
        }

        if (Optional.ofNullable(session.getEndTime()).isPresent()) {
            dbSession.setEndTime(session.getEndTime());
        }
    }

}
