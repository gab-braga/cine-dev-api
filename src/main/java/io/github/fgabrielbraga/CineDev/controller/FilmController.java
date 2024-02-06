package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.FilmInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.FilmOutputDTO;
import io.github.fgabrielbraga.CineDev.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{uuid}")
    public ResponseEntity<?> findById(@PathVariable UUID uuid) {
        Optional<FilmOutputDTO> filmOptional = filmService.findById(uuid);
        return filmOptional
                .map(filmFound -> ResponseEntity.ok(filmFound))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genres) {
        List<FilmOutputDTO> films = filmService.findAll(title, genres);
        return ResponseEntity.ok(films);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody FilmInputDTO film) {
        film.setUuid(null);
        FilmOutputDTO filmSaved = filmService.save(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmSaved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(
            @PathVariable UUID uuid,
            @RequestBody FilmInputDTO film) {
        Optional<FilmOutputDTO> filmOptional = filmService.findById(uuid);
        return filmOptional
                .map(filmFound -> {
                    film.setUuid(uuid);
                    FilmOutputDTO filmUpdated = filmService.update(film);
                    return ResponseEntity.ok(filmUpdated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid) {
        Optional<FilmOutputDTO> filmOptional = filmService.findById(uuid);
        return filmOptional
                .map(filmFound -> {
                    filmService.deleteById(uuid);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
