package br.com.itau.letscode.agrocinetickets.Ticket.controller;

import br.com.itau.letscode.agrocinetickets.Ticket.model.Ticket;
import br.com.itau.letscode.agrocinetickets.Ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> findAll() {
        return ResponseEntity.ok().body(ticketService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(ticketService.findById(id));
    }

    @PostMapping(value = "/commit")
    public ResponseEntity<Ticket> commit(
            @RequestParam(value = "sessionId", defaultValue = "") UUID sessionId,
            @RequestParam(value = "line", defaultValue = "") int line,
            @RequestParam(value = "column", defaultValue = "") int column
    ) {
        Ticket ticket = ticketService.commit(sessionId, line, column);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ticket.getId()).toUri();
        return ResponseEntity.created(uri).body(ticket);
    }

}
