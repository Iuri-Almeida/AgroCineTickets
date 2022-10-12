package br.com.itau.letscode.agrocinetickets.Room.repository;

import br.com.itau.letscode.agrocinetickets.Room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
}
