package io.github.fgabrielbraga.CineDev.repository;

import io.github.fgabrielbraga.CineDev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE users u SET u.disable = 1 WHERE uuid = ?", nativeQuery = true)
    void disableUserById(UUID uuid);

    @Modifying
    @Query(value = "UPDATE users u SET u.disable = 0 WHERE uuid = ?", nativeQuery = true)
    void enableUserById(UUID uuid);
}
