package io.github.fgabrielbraga.CineDev.repository;

import io.github.fgabrielbraga.CineDev.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    @Query(value = "SELECT * FROM rooms r WHERE " +
            "r.number_room = IFNULL(?, r.number_room)", nativeQuery = true)
    List<Room> findAllWithFilter(Short number);
}
