package io.github.fgabrielbraga.CineDev.repository;

import io.github.fgabrielbraga.CineDev.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    @Query(value = "SELECT * FROM tickets WHERE session_uuid = ?", nativeQuery = true)
    List<Ticket> findBySessionId(UUID uuid);

    @Query(value = "SELECT * FROM tickets WHERE uuid IN ?", nativeQuery = true)
    List<Ticket> findByIdIn(List<UUID> uuids);
}
