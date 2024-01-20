package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.FilmInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.FilmOutputDTO;
import io.github.fgabrielbraga.CineDev.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping("/{uuid}")
    public ResponseEntity<FilmOutputDTO> findById(@PathVariable UUID uuid) {
        Optional<FilmOutputDTO> filmOptional = filmService.findById(uuid);
        return filmOptional
                .map(filmFound -> ResponseEntity.ok(filmFound))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<FilmOutputDTO>> findAll(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genres) {
        List<FilmOutputDTO> films = filmService.findAll(title, genres);
        return ResponseEntity.ok(films);
    }

    @PostMapping
    public ResponseEntity<FilmOutputDTO> create(@RequestBody FilmInputDTO film) {
        film.setUuid(null);
        FilmOutputDTO filmSaved = filmService.save(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmSaved);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<FilmOutputDTO> update(@PathVariable UUID uuid, @RequestBody FilmInputDTO film) {
        Optional<FilmOutputDTO> filmOptional = filmService.findById(uuid);
        return filmOptional
                .map(filmFound -> {
                    film.setUuid(uuid);
                    FilmOutputDTO filmUpdated = filmService.update(film);
                    return ResponseEntity.ok(filmUpdated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

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
