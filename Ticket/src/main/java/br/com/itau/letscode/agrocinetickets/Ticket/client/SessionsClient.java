package br.com.itau.letscode.agrocinetickets.Ticket.client;

import br.com.itau.letscode.agrocinetickets.Ticket.model.Session;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "sessions-service")
public interface SessionsClient {

    @GetMapping(value = "/sessions/{id}")
    ResponseEntity<Session> findById(@PathVariable UUID id);

    @GetMapping(value = "/sessions/{id}/isSeatOccupied")
    ResponseEntity<Boolean> isSeatOccupied(
            @PathVariable UUID id,
            @RequestParam(value = "line", defaultValue = "") int line,
            @RequestParam(value = "column", defaultValue = "") int column
    );

    @PutMapping(value = "/sessions/{id}/occupySeat")
    ResponseEntity<Void> occupySeat(
            @PathVariable UUID id,
            @RequestParam(value = "line", defaultValue = "") int line,
            @RequestParam(value = "column", defaultValue = "") int column
    );

}
