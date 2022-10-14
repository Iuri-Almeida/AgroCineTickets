package br.com.itau.letscode.agrocinetickets.Session.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "rooms-service")
public interface RoomClient {

    @GetMapping(value = "/rooms/{id}/lines")
    ResponseEntity<Integer> getLines(@PathVariable UUID id);

    @GetMapping(value = "/rooms/{id}/columns")
    ResponseEntity<Integer> getColumns(@PathVariable UUID id);

}
