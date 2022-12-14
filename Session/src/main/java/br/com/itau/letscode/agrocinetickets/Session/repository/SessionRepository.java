package br.com.itau.letscode.agrocinetickets.Session.repository;

import br.com.itau.letscode.agrocinetickets.Session.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {
}
