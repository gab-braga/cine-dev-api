package io.github.fgabrielbraga.CineDev.repository;

import io.github.fgabrielbraga.CineDev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT u.* FROM users u " +
            "WHERE u.name LIKE CONCAT('%', IFNULL(?, ''), '%') " +
            "AND u.email LIKE CONCAT(IFNULL(?, ''), '%') " +
            "AND u.cpf LIKE CONCAT(IFNULL(?, ''), '%') " +
            "ORDER BY u.name " +
            "LIMIT 1000", nativeQuery = true)
    List<User> findTop1000ByNameAndEmailAndCpf(String name, String email, String cpf);

    @Modifying
    @Query(value = "UPDATE users u SET u.active = 0 WHERE u.uuid = ?", nativeQuery = true)
    void disableUserById(UUID uuid);

    @Modifying
    @Query(value = "UPDATE users u SET u.active = 1 WHERE u.uuid = ?", nativeQuery = true)
    void enableUserById(UUID uuid);
}
