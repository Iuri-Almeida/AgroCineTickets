package br.com.itau.letscode.agrocinetickets.Room.controller;

import br.com.itau.letscode.agrocinetickets.Room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping(value = "/{id}/isSeatOccupied")
    public ResponseEntity<Boolean> isSeatOccupied(
            @PathVariable UUID id,
            @RequestParam(value = "line", defaultValue = "") int line,
            @RequestParam(value = "column", defaultValue = "") int column
    ) {
        return ResponseEntity.ok().body(roomService.isSeatOccupied(id, line, column));
    }

    @PatchMapping(value = "/{id}/occupySeat")
    public ResponseEntity<Void> occupySeat(
            @PathVariable UUID id,
            @RequestParam(value = "line", defaultValue = "") int line,
            @RequestParam(value = "column", defaultValue = "") int column
    ) {
        roomService.occupySeat(id, line, column);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}/vacateSeat")
    public ResponseEntity<Void> vacateSeat(
            @PathVariable UUID id,
            @RequestParam(value = "line", defaultValue = "") int line,
            @RequestParam(value = "column", defaultValue = "") int column
    ) {
        roomService.vacateSeat(id, line, column);
        return ResponseEntity.noContent().build();
    }

}
