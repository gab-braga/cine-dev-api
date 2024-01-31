package io.github.fgabrielbraga.CineDev.repository;

import io.github.fgabrielbraga.CineDev.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    @Query(value = "SELECT t.* FROM tickets t JOIN seats se ON t.seat_id = se.uuid " +
            "WHERE t.session_id = ? ORDER BY se.position", nativeQuery = true)
    List<Ticket> findBySessionId(UUID uuid);
}
