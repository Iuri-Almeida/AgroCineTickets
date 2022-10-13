package br.com.itau.letscode.agrocinetickets.Ticket.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "rooms-service")
public interface RoomsClient {

    @GetMapping(value = "/rooms/{id}/isSeatOccupied")
    ResponseEntity<Boolean> isSeatOccupied(
            @PathVariable UUID id,
            @RequestParam(value = "line", defaultValue = "") int line,
            @RequestParam(value = "column", defaultValue = "") int column
    );

    @PutMapping(value = "/rooms/{id}/occupySeat")
    ResponseEntity<Void> occupySeat(
            @PathVariable UUID id,
            @RequestParam(value = "line", defaultValue = "") int line,
            @RequestParam(value = "column", defaultValue = "") int column
    );

}
