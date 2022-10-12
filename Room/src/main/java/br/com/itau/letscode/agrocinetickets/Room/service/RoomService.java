package br.com.itau.letscode.agrocinetickets.Room.service;

import br.com.itau.letscode.agrocinetickets.Room.exception.RoomNotFoundException;
import br.com.itau.letscode.agrocinetickets.Room.exception.SeatOutOfBoundsException;
import br.com.itau.letscode.agrocinetickets.Room.model.Room;
import br.com.itau.letscode.agrocinetickets.Room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public boolean isSeatOccupied(UUID id, int line, int column) {
        this.validateSeat(id, line, column);
        return this.findById(id).getSeats()[line][column];
    }

    public void occupySeat(UUID id, int line, int column) {
        if (!this.isSeatOccupied(id, line, column)) {
            Room room = this.findById(id);
            room.occupySeat(line, column);

            this.save(room);
        }
    }

    public void vacateSeat(UUID id, int line, int column) {
        if (this.isSeatOccupied(id, line, column)) {
            Room room = this.findById(id);
            room.vacateSeat(line, column);

            this.save(room);
        }
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
        return line - 1 > this.findById(id).getLines();
    }

    private boolean isColumnGreaterThanTotal(UUID id, int column) {
        return column - 1 > this.findById(id).getColumns();
    }

    private Room findById(UUID id) {
        return roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room with id = " + id + " not found."));
    }

    private void save(Room room) {
        roomRepository.save(room);
    }

}
