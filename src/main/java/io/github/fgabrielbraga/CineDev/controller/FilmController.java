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
        Optional<FilmDTO> filmOpt = filmService.findById(uuid);
        if(filmOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(filmOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<FilmDTO>> findAllWithFilter(@RequestParam(required = false) String title) {
        if(title != null) {
            List<FilmDTO> films = filmService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(films);
        } else {
            List<FilmDTO> films =  filmService.findByTitleContaining(title);
            return ResponseEntity.status(HttpStatus.OK).body(films);
        }
    }

    @GetMapping("/genres/{genres}")
    public ResponseEntity<List<FilmDTO>> findByGenresContaining(@PathVariable String genres) {
        List<FilmDTO> films =  filmService.findByGenresContaining(genres);
        return ResponseEntity.status(HttpStatus.OK).body(films);
    }

    @PostMapping
    public ResponseEntity<FilmDTO> save(@RequestBody FilmDTO film) {
        film.setUuid(null);
        FilmDTO filmSaved = filmService.save(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmSaved);
    }

    @PutMapping
    public ResponseEntity<FilmDTO> update(@RequestBody FilmDTO film) {
        Optional<FilmDTO> filmOpt = filmService.findById(film.getUuid());
        if(filmOpt.isPresent()) {
            FilmDTO filmSaved = filmService.save(film);
            return ResponseEntity.status(HttpStatus.CREATED).body(filmSaved);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping
    public ResponseEntity<FilmDTO> delete(@RequestParam UUID uuid) {
        Optional<FilmDTO> filmOpt = filmService.findById(uuid);
        if(filmOpt.isPresent()) {
            FilmDTO filmDeleted = filmService.delete(filmOpt.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(filmDeleted);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
