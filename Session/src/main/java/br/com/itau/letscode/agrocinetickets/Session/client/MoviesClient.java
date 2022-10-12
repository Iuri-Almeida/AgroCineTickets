package br.com.itau.letscode.agrocinetickets.Session.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "movies-service")
public interface MoviesClient {

    @GetMapping(value = "/{id}/rated")
    ResponseEntity<Integer> rated(@PathVariable UUID id);

}
