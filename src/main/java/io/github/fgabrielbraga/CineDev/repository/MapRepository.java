package io.github.fgabrielbraga.CineDev.repository;

import io.github.fgabrielbraga.CineDev.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MapRepository extends JpaRepository<Map, UUID> {

    @Query(value = "SELECT m.* FROM maps m " +
            "JOIN rooms r ON r.map_uuid = m.uuid " +
            "WHERE r.uuid = ?", nativeQuery = true)
    Optional<Map> findMapByRoomId(UUID uuid);
}
