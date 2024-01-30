package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.output.FilmOutputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.SessionOutputDTO;
import io.github.fgabrielbraga.CineDev.service.FilmService;
import io.github.fgabrielbraga.CineDev.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private FilmService filmService;

    @GetMapping("/sessions")
    public ResponseEntity<List<SessionOutputDTO>> findSessionsRecentByDate(
            @RequestParam(required = false) LocalDate date) {
        List<SessionOutputDTO> sessions = sessionService.findRecentByDate(date);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/sessions/{uuid}")
    public ResponseEntity<SessionOutputDTO> findSessionById(
            @PathVariable UUID uuid) {
        Optional<SessionOutputDTO> sessionOpt = sessionService.findByIdForClient(uuid);
        return sessionOpt.map(session -> {
            return ResponseEntity.ok(session);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/sessions/week")
    public ResponseEntity<List<SessionOutputDTO>> findAllSessionsThisWeek() {
        List<SessionOutputDTO> sessions = sessionService.findThisWeek();
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/sessions/genres")
    public ResponseEntity<List<SessionOutputDTO>> findSessionsByGenres(
            @RequestParam String genres) {
        List<SessionOutputDTO> sessions = sessionService.findByGenresForClient(genres);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/films/{uuid}")
    public ResponseEntity<FilmOutputDTO> findFilmById(
            @PathVariable UUID uuid) {
        Optional<FilmOutputDTO> filmOpt = filmService.findByIdForClient(uuid);
        return filmOpt.map(film -> {
            return ResponseEntity.ok(film);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/films/genres")
    public ResponseEntity<List<FilmOutputDTO>> findFilmByGenres(
            @RequestParam String genres) {
        List<FilmOutputDTO> films = filmService.findByGenresForClient(genres);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/films")
    public ResponseEntity<List<FilmOutputDTO>> findFilms() {
        List<FilmOutputDTO> films = filmService.findForClient();
        return ResponseEntity.ok(films);
    }
}
