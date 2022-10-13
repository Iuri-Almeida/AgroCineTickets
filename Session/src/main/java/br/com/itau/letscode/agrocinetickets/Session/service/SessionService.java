package br.com.itau.letscode.agrocinetickets.Session.service;

import br.com.itau.letscode.agrocinetickets.Session.exception.SessionNotFoundException;
import br.com.itau.letscode.agrocinetickets.Session.model.Session;
import br.com.itau.letscode.agrocinetickets.Session.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    public Session findById(UUID id) {
        return sessionRepository.findById(id).orElseThrow(() -> new SessionNotFoundException("Session with id = " + id + " not found."));
    }

}
