package io.github.fgabrielbraga.CineDev.repository;

import io.github.fgabrielbraga.CineDev.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FilmRepository extends JpaRepository<Film, UUID> {

    List<Film> findByTitleContainingIgnoreCase(String title);

    List<Film> findByGenresContainingIgnoreCase(String genres);
}
