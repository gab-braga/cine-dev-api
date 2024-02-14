package io.github.fgabrielbraga.CineDev.repository;

import io.github.fgabrielbraga.CineDev.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    @Query(value = "SELECT * FROM reservations " +
            "ORDER BY reserved_at " +
            "LIMIT 1000", nativeQuery = true)
    List<Reservation> findTop1000();

    @Query(value = "SELECT * FROM reservations " +
            "WHERE session_uuid = ? " +
            "ORDER BY reserved_at " +
            "LIMIT 1000", nativeQuery = true)
    List<Reservation> findTop1000BySessionId(UUID uuid);

    @Query(value = "SELECT * FROM reservations " +
            "WHERE user_uuid = ? " +
            "ORDER BY reserved_at DESC " +
            "LIMIT 1000", nativeQuery = true)
    List<Reservation> findTop1000ByUserId(UUID uuid);
}