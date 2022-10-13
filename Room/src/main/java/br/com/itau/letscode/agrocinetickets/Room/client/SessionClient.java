package br.com.itau.letscode.agrocinetickets.Room.client;

import br.com.itau.letscode.agrocinetickets.Room.model.Session;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "sessions-service")
public interface SessionClient {

    @GetMapping(value = "/{id}")
    ResponseEntity<Session> findById(@PathVariable UUID id);

}
