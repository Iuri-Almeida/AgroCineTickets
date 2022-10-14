package br.com.itau.letscode.agrocinetickets.Session.controller;

import br.com.itau.letscode.agrocinetickets.Session.model.Session;
import br.com.itau.letscode.agrocinetickets.Session.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping
    public ResponseEntity<List<Session>> findAll() {
        return ResponseEntity.ok().body(sessionService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Session> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(sessionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Session> insert(@RequestBody Session session) {
        session = sessionService.insert(session);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(session.getId()).toUri();
        return ResponseEntity.created(uri).body(session);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Session> update(@PathVariable UUID id, @RequestBody Session session) {
        return ResponseEntity.ok().body(sessionService.update(id, session));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        sessionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/isSeatOccupied")
    public ResponseEntity<Boolean> isSeatOccupied(
            @PathVariable UUID id,
            @RequestParam(value = "line", defaultValue = "") int line,
            @RequestParam(value = "column", defaultValue = "") int column
    ) {
        return ResponseEntity.ok().body(sessionService.isSeatOccupied(id, line, column));
    }

    @PutMapping(value = "/{id}/occupySeat")
    public ResponseEntity<Void> occupySeat(
            @PathVariable UUID id,
            @RequestParam(value = "line", defaultValue = "") int line,
            @RequestParam(value = "column", defaultValue = "") int column
    ) {
        sessionService.occupySeat(id, line, column);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}/vacateSeat")
    public ResponseEntity<Void> vacateSeat(
            @PathVariable UUID id,
            @RequestParam(value = "line", defaultValue = "") int line,
            @RequestParam(value = "column", defaultValue = "") int column
    ) {
        sessionService.vacateSeat(id, line, column);
        return ResponseEntity.noContent().build();
    }

}
