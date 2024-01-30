package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.output.FilmOutputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.SessionOutputDTO;
import io.github.fgabrielbraga.CineDev.service.FilmService;
import io.github.fgabrielbraga.CineDev.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/sessions/week")
    public ResponseEntity<List<SessionOutputDTO>> findAllSessionsThisWeek() {
        List<SessionOutputDTO> sessions = sessionService.findThisWeek();
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/films")
    public ResponseEntity<List<FilmOutputDTO>> findFilms() {
        List<FilmOutputDTO> films = filmService.findForClient();
        return ResponseEntity.ok(films);
    }
}
