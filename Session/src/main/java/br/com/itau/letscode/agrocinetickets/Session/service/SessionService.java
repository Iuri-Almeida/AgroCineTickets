package br.com.itau.letscode.agrocinetickets.Session.service;

import br.com.itau.letscode.agrocinetickets.Session.client.RoomClient;
import br.com.itau.letscode.agrocinetickets.Session.exception.RoomResourceNullPointerException;
import br.com.itau.letscode.agrocinetickets.Session.exception.SeatOutOfBoundsException;
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
        int lines = this.getRoomLines(session.getRoomId());
        int columns = this.getRoomColumns(session.getRoomId());

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

    public boolean isSeatOccupied(UUID id, int line, int column) {
        this.validateSeat(id, line, column);
        return this.findById(id).getSeats()[line][column];
    }

    public void occupySeat(UUID id, int line, int column) {
        if (!this.isSeatOccupied(id, line, column)) {
            Session session = this.findById(id);
            session.occupySeat(line, column);

            sessionRepository.save(session);
        }
    }

    public void vacateSeat(UUID id, int line, int column) {
        if (this.isSeatOccupied(id, line, column)) {
            Session session = this.findById(id);
            session.vacateSeat(line, column);

            sessionRepository.save(session);
        }
    }

    private int getRoomLines(UUID roomId) {
        return Optional.ofNullable(roomClient.getLines(roomId).getBody()).orElseThrow(() -> new RoomResourceNullPointerException("Lines value returned is null."));
    }

    private int getRoomColumns(UUID roomId) {
        return Optional.ofNullable(roomClient.getColumns(roomId).getBody()).orElseThrow(() -> new RoomResourceNullPointerException("Columns value returned is null."));
    }

    private void validateSeat(UUID id, int line, int column) {
        this.validateLine(id, line);
        this.validateColumn(id, column);
    }

    private void validateLine(UUID id, int line) {
        if (this.isLessThanZero(line) || this.isLineGreaterThanTotal(id, line)) {
            throw new SeatOutOfBoundsException("The line = " + line + " does not exist.");
        }
    }

    private void validateColumn(UUID id, int column) {
        if (this.isLessThanZero(column) || this.isColumnGreaterThanTotal(id, column)) {
            throw new SeatOutOfBoundsException("The column = " + column + " does not exist.");
        }
    }

    private boolean isLessThanZero(int value) {
        return value < 0;
    }

    private boolean isLineGreaterThanTotal(UUID id, int line) {
        return line - 1 > this.getRoomLines(this.findById(id).getRoomId());
    }

    private boolean isColumnGreaterThanTotal(UUID id, int column) {
        return column - 1 > this.getRoomColumns(this.findById(id).getRoomId());
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
