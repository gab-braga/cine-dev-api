package io.github.fgabrielbraga.CineDev.repository;

import io.github.fgabrielbraga.CineDev.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AreaRepository extends JpaRepository<Area, UUID> {

    @Query(value = "SELECT a.* FROM areas a " +
            "JOIN maps m ON a.map_uuid = m.uuid " +
            "JOIN rooms r ON m.room_uuid = r.uuid " +
            "WHERE r.uuid = ?", nativeQuery = true)
    List<Area> findByRoomId(UUID uuid);

    @Modifying
    @Query(value = "DELETE FROM areas a " +
            "JOIN maps m ON a.map_uuid = m.uuid " +
            "WHERE m.room_uuid = ?", nativeQuery = true)
    void deleteAllByRoomId(UUID uuid);
}
