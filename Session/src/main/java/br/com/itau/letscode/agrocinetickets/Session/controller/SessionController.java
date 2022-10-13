package br.com.itau.letscode.agrocinetickets.Session.controller;

import br.com.itau.letscode.agrocinetickets.Session.model.Session;
import br.com.itau.letscode.agrocinetickets.Session.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Session> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(sessionService.findById(id));
    }

}
