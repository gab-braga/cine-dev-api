package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.FilmDTO;
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
    public ResponseEntity<FilmDTO> findById(@PathVariable UUID uuid) {
        Optional<FilmDTO> filmOptional = filmService.findById(uuid);
        return filmOptional
                .map(filmFound -> ResponseEntity.ok(filmFound))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<FilmDTO>> findAllWithFilter(@RequestParam(required = false) String title) {
        List<FilmDTO> films = (title == null) ? filmService.findAll() : filmService.findByTitleContaining(title);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/genres/{genres}")
    public ResponseEntity<List<FilmDTO>> findByGenresContaining(@PathVariable String genres) {
        List<FilmDTO> films =  filmService.findByGenresContaining(genres);
        return ResponseEntity.ok(films);
    }

    @PostMapping
    public ResponseEntity<FilmDTO> create(@RequestBody FilmDTO film) {
        film.setUuid(null);
        FilmDTO filmSaved = filmService.save(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmSaved);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<FilmDTO> update(@PathVariable UUID uuid, @RequestBody FilmDTO film) {
        Optional<FilmDTO> filmOptional = filmService.findById(uuid);
        return filmOptional
                .map(filmFound -> {
                    film.setUuid(uuid);
                    FilmDTO filmUpdated = filmService.save(film);
                    return ResponseEntity.ok(filmUpdated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<FilmDTO> delete(@PathVariable UUID uuid) {
        Optional<FilmDTO> filmOptional = filmService.findById(uuid);
        return filmOptional
                .map(filmFound -> {
                    FilmDTO filmDeleted = filmService.delete(filmFound);
                    return ResponseEntity.ok(filmDeleted);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
