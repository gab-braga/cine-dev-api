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
import java.util.UUID;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private FilmService filmService;

    @GetMapping("/sessions")
    public ResponseEntity<?> findTop1000SessionsRecentByDate(
            @RequestParam(required = false) LocalDate date) {
        List<SessionOutputDTO> sessions = sessionService.findTop1000RecentByDate(date);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/sessions/{uuid}")
    public ResponseEntity<?> findSessionById(@PathVariable UUID uuid) {
        SessionOutputDTO session = sessionService.findById(uuid);
        return ResponseEntity.ok(session);
    }

    @GetMapping("/sessions/week")
    public ResponseEntity<?> findTop1000SessionsThisWeek() {
        List<SessionOutputDTO> sessions = sessionService.findTop1000ThisWeek();
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/sessions/genres")
    public ResponseEntity<?> findTop1000SessionsByGenres(@RequestParam String genres) {
        List<SessionOutputDTO> sessions = sessionService.findTop1000ByGenresForClient(genres);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/sessions/films/{uuid}")
    public ResponseEntity<?> findTop1000SessionsByFilmId(@PathVariable UUID uuid) {
        List<SessionOutputDTO> sessions = sessionService.findTop1000ByFilmId(uuid);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/films")
    public ResponseEntity<?> findTop1000Films() {
        List<FilmOutputDTO> films = filmService.findTop1000();
        return ResponseEntity.ok(films);
    }

    @GetMapping("/films/{uuid}")
    public ResponseEntity<?> findFilmById(@PathVariable UUID uuid) {
        FilmOutputDTO film = filmService.findById(uuid);
        return ResponseEntity.ok(film);
    }

    @GetMapping("/films/genres")
    public ResponseEntity<?> findTop1000FilmsByGenres(@RequestParam String genres) {
        List<FilmOutputDTO> films = filmService.findTop1000ByGenres(genres);
        return ResponseEntity.ok(films);
    }
}
