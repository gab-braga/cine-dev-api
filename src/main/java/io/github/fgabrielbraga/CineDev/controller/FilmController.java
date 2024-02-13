package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.FilmInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.FilmOutputDTO;
import io.github.fgabrielbraga.CineDev.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{uuid}")
    public ResponseEntity<?> findById(@PathVariable UUID uuid) {
        FilmOutputDTO filmFound = filmService.findById(uuid);
        return ResponseEntity.ok(filmFound);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> findTop1000ByTitleAndGenres(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genres) {
        List<FilmOutputDTO> films = filmService.findTop1000ByTitleAndGenres(title, genres);
        return ResponseEntity.ok(films);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody FilmInputDTO film) {
        FilmOutputDTO filmSaved = filmService.save(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmSaved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(
            @PathVariable UUID uuid,
            @Valid @RequestBody FilmInputDTO film) {
        film.setUuid(uuid);
        FilmOutputDTO filmUpdated = filmService.update(film);
        return ResponseEntity.ok(filmUpdated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid) {
        filmService.deleteById(uuid);
        return ResponseEntity.ok().build();
    }
}
