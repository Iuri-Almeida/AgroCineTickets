package br.com.itau.letscode.agrocinetickets.Session.controller;

import br.com.itau.letscode.agrocinetickets.Session.model.Session;
import br.com.itau.letscode.agrocinetickets.Session.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/commit")
    public ResponseEntity<Void> chooseSeat(
            @RequestBody Session session,
            @RequestParam(value = "line", defaultValue = "") int line,
            @RequestParam(value = "column", defaultValue = "") int column
    ) {
        sessionService.chooseSeat(session, line, column);
        return ResponseEntity.noContent().build();
    }

}
