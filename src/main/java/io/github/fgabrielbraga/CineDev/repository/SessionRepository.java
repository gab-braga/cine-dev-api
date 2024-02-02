package io.github.fgabrielbraga.CineDev.repository;

import io.github.fgabrielbraga.CineDev.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    @Modifying
    @Query(value = "UPDATE sessions s SET s.open = 0 WHERE s.uuid = ?", nativeQuery = true)
    void closeSessionById(UUID uuid);

    @Modifying
    @Query(value = "UPDATE sessions s SET s.open = 1 WHERE s.uuid = ?", nativeQuery = true)
    void openSessionById(UUID uuid);

    @Query(value = "SELECT s.* FROM sessions s " +
            "JOIN rooms r ON s.room_uuid = r.uuid " +
            "JOIN films f ON s.film_uuid = f.uuid WHERE " +
            "s.session_date = IFNULL(?, s.session_date) AND " +
            "r.room_number = IFNULL(?, r.room_number) AND " +
            "f.title LIKE CONCAT('%', IFNULL(?, ''), '%') " +
            "ORDER BY session_date", nativeQuery = true)
    List<Session> findAllWithFilter(LocalDate date, Short number, String title);

    @Query(value = "SELECT * FROM sessions WHERE room_uuid = ? " +
            "ORDER BY session_date", nativeQuery = true)
    List<Session> findByRoomId(UUID uuid);

    @Query(value = "SELECT * FROM sessions " +
            "WHERE open = 1 AND WEEK(session_date) = WEEK(NOW()) " +
            "ORDER BY session_date", nativeQuery = true)
    List<Session> findThisWeek();

    @Query(value = "SELECT s.* FROM sessions s " +
            "JOIN films f ON s.film_uuid = f.uuid " +
            "WHERE s.open = 1 AND s.session_date >= CURDATE() " +
            "AND s.session_date = IFNULL(?, s.session_date) " +
            "ORDER BY session_date LIMIT 120", nativeQuery = true)
    List<Session> findRecentByDate(LocalDate date);

    @Query(value = "SELECT s.* FROM sessions s " +
            "JOIN films f ON s.film_uuid = f.uuid " +
            "WHERE s.open = 1 AND s.session_date >= CURDATE() " +
            "AND f.genres LIKE CONCAT('%', IFNULL(?, ''), '%') " +
            "ORDER BY session_date LIMIT 120", nativeQuery = true)
    List<Session> findByGenresForClient(String genres);
}
