package br.com.itau.letscode.agrocinetickets.Ticket.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "movies-service")
public interface MoviesClient {

    @GetMapping(value = "/movies/{id}/rated")
    ResponseEntity<Integer> rated(@PathVariable UUID id);

}
