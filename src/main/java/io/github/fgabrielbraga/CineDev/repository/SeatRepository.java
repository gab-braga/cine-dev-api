package io.github.fgabrielbraga.CineDev.repository;

import io.github.fgabrielbraga.CineDev.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SeatRepository extends JpaRepository<Seat, UUID> {

    @Query(value = "SELECT s.* FROM seats s JOIN rooms r ON s.room_id = r.uuid " +
            "WHERE r.uuid = ? ORDER BY s.position", nativeQuery = true)
    List<Seat> findByRoomId(UUID uuid);

    @Modifying
    @Query(value = "DELETE FROM seats WHERE room_id = ?", nativeQuery = true)
    void deleteAllByRoomId(UUID uuid);
}
