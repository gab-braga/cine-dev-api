package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.SessionInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.FilmOutputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.ReservationOutputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.RoomOutputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.SessionOutputDTO;
import io.github.fgabrielbraga.CineDev.service.FilmService;
import io.github.fgabrielbraga.CineDev.service.ReservationService;
import io.github.fgabrielbraga.CineDev.service.RoomService;
import io.github.fgabrielbraga.CineDev.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private FilmService filmService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ReservationService reservationService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{uuid}")
    public ResponseEntity<SessionOutputDTO> findById(@PathVariable UUID uuid) {
        Optional<SessionOutputDTO> sessionOptional = sessionService.findById(uuid);
        return sessionOptional
                .map(sessionFound -> ResponseEntity.ok(sessionFound))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<SessionOutputDTO>> findAll(
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) Short number,
            @RequestParam(required = false) String title) {
        List<SessionOutputDTO> sessions = sessionService.findAll(date, number, title);
        return ResponseEntity.ok(sessions);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SessionOutputDTO> create(@RequestBody SessionInputDTO session) {
        UUID filmId = session.getFilmId();
        UUID roomId = session.getRoomId();
        Optional<FilmOutputDTO> filmOptional = filmService.findById(filmId);
        Optional<RoomOutputDTO> roomOptional = roomService.findById(roomId);
        if(filmOptional.isPresent() && roomOptional.isPresent()) {
            session.setUuid(null);
            SessionOutputDTO sessionSaved = sessionService.save(filmId, roomId, session);
            return ResponseEntity.status(HttpStatus.CREATED).body(sessionSaved);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{uuid}")
    public ResponseEntity<SessionOutputDTO> update(@PathVariable UUID uuid, @RequestBody SessionInputDTO session) {
        List<ReservationOutputDTO> reservations = reservationService.findBySessionId(uuid);
        if(reservations.isEmpty()) {
            UUID filmId = session.getFilmId();
            UUID roomId = session.getRoomId();
            Optional<FilmOutputDTO> filmOptional = filmService.findById(filmId);
            Optional<RoomOutputDTO> roomOptional = roomService.findById(roomId);
            if(filmOptional.isPresent() && roomOptional.isPresent()) {
                Optional<SessionOutputDTO> sessionOptional = sessionService.findById(uuid);
                return sessionOptional
                        .map(sessionFound -> {
                            session.setUuid(uuid);
                            SessionOutputDTO sessionUpdated = sessionService.update(filmId, roomId, session);
                            return ResponseEntity.ok(sessionUpdated);
                        })
                        .orElseGet(() -> ResponseEntity.notFound().build());
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{uuid}/close")
    public ResponseEntity<?> close(@PathVariable UUID uuid) {
        Optional<SessionOutputDTO> sessionOptional = sessionService.findById(uuid);
        return sessionOptional
                .map(sessionFound -> {
                    sessionService.close(uuid);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{uuid}/open")
    public ResponseEntity<?> open(@PathVariable UUID uuid) {
        Optional<SessionOutputDTO> sessionOptional = sessionService.findById(uuid);
        return sessionOptional
                .map(sessionFound -> {
                    sessionService.open(uuid);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
