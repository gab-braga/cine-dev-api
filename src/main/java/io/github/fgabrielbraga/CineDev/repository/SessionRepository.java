package io.github.fgabrielbraga.CineDev.repository;

import io.github.fgabrielbraga.CineDev.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    @Query(value = "SELECT s.* FROM sessions s " +
            "JOIN rooms r ON s.room_id = r.uuid " +
            "JOIN films f ON s.film_id = f.uuid WHERE " +
            "s.date_session = IFNULL(?, s.date_session) AND " +
            "r.number_room = IFNULL(?, r.number_room) AND " +
            "f.title LIKE CONCAT('%', IFNULL(?, ''), '%')", nativeQuery = true)
    List<Session> findAllWithFilter(LocalDate date, Short number, String title);
}
