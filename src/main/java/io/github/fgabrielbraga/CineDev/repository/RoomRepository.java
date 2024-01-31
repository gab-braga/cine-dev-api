package io.github.fgabrielbraga.CineDev.repository;

import io.github.fgabrielbraga.CineDev.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    @Query(value = "SELECT * FROM rooms r WHERE " +
            "r.number_room = IFNULL(?, r.number_room)", nativeQuery = true)
    List<Room> findAllWithFilter(Short number);

    @Query(value = "SELECT r.* FROM rooms r JOIN sessions s " +
            "ON r.uuid = s.room_id WHERE s.uuid = ?", nativeQuery = true)
    Optional<Room> findBySessionId(UUID uuid);
}
