package br.com.itau.letscode.agrocinetickets.Room.controller;

import br.com.itau.letscode.agrocinetickets.Room.model.Room;
import br.com.itau.letscode.agrocinetickets.Room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> findAll() {
        return ResponseEntity.ok().body(roomService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Room> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(roomService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Room> insert(@RequestBody Room room) {
        room = roomService.insert(room);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(room.getId()).toUri();
        return ResponseEntity.created(uri).body(room);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Room> update(@PathVariable UUID id, @RequestParam Room room) {
        return ResponseEntity.ok().body(roomService.update(id, room));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        roomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/isSeatOccupied")
    public ResponseEntity<Boolean> isSeatOccupied(
            @PathVariable UUID id,
            @RequestParam(value = "line", defaultValue = "") int line,
            @RequestParam(value = "column", defaultValue = "") int column
    ) {
        return ResponseEntity.ok().body(roomService.isSeatOccupied(id, line, column));
    }

    @PutMapping(value = "/{id}/occupySeat")
    public ResponseEntity<Void> occupySeat(
            @PathVariable UUID id,
            @RequestParam(value = "line", defaultValue = "") int line,
            @RequestParam(value = "column", defaultValue = "") int column
    ) {
        roomService.occupySeat(id, line, column);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}/vacateSeat")
    public ResponseEntity<Void> vacateSeat(
            @PathVariable UUID id,
            @RequestParam(value = "line", defaultValue = "") int line,
            @RequestParam(value = "column", defaultValue = "") int column
    ) {
        roomService.vacateSeat(id, line, column);
        return ResponseEntity.noContent().build();
    }

}
