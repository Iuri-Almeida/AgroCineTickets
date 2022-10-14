package br.com.itau.letscode.agrocinetickets.Room.service;

import br.com.itau.letscode.agrocinetickets.Room.exception.RoomNotFoundException;
import br.com.itau.letscode.agrocinetickets.Room.model.Room;
import br.com.itau.letscode.agrocinetickets.Room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findById(UUID id) {
        return roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room with id = " + id + " not found."));
    }

    public Room insert(Room room) {
        return roomRepository.save(new Room(null, room.getColumns(), room.getLines()));
    }

    public Room update(UUID id, Room room) {
        Room dbRoom = this.findById(id);
        this.updateData(dbRoom, room);
        return roomRepository.save(dbRoom);
    }

    public void deleteById(UUID id) {
        roomRepository.deleteById(id);
    }

    public int getLines(UUID id) {
        return this.findById(id).getLines();
    }

    public int getColumns(UUID id) {
        return this.findById(id).getColumns();
    }

    private void updateData(Room dbRoom, Room room) {
        if (Optional.ofNullable(room.getColumns()).isPresent()) {
            dbRoom.setColumns(room.getColumns());
        }

        if (Optional.ofNullable(room.getLines()).isPresent()) {
            dbRoom.setLines(room.getLines());
        }

    }

}
