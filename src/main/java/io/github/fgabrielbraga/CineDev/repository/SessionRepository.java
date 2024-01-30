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
            "JOIN rooms r ON s.room_id = r.uuid " +
            "JOIN films f ON s.film_id = f.uuid WHERE " +
            "s.date_session = IFNULL(?, s.date_session) AND " +
            "r.number_room = IFNULL(?, r.number_room) AND " +
            "f.title LIKE CONCAT('%', IFNULL(?, ''), '%') " +
            "ORDER BY date_session", nativeQuery = true)
    List<Session> findAllWithFilter(LocalDate date, Short number, String title);

    @Query(value = "SELECT * FROM sessions WHERE room_id = ? ORDER BY date_session", nativeQuery = true)
    List<Session> findByRoomId(UUID uuid);

    @Query(value = "SELECT * FROM sessions WHERE open = 1 AND WEEK(date_session) = WEEK(NOW()) ORDER BY date_session", nativeQuery = true)
    List<Session> findThisWeek();

    @Query(value = "SELECT s.* FROM sessions s JOIN films f ON s.film_id = f.uuid " +
            "WHERE s.open = 1 AND s.date_session >= CURDATE() " +
            "AND s.date_session = IFNULL(?, s.date_session) " +
            "ORDER BY date_session LIMIT 120", nativeQuery = true)
    List<Session> findRecentByDate(LocalDate date);

    @Query(value = "SELECT s.* FROM sessions s JOIN films f ON s.film_id = f.uuid " +
            "WHERE s.open = 1 AND s.date_session >= CURDATE() " +
            "AND f.genres LIKE CONCAT('%', IFNULL(?, ''), '%') " +
            "ORDER BY date_session LIMIT 120", nativeQuery = true)
    List<Session> findByGenresForClient(String genres);
}
