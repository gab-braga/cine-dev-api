package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.SessionInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.SessionOutputDTO;
import io.github.fgabrielbraga.CineDev.service.SessionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) Short number,
            @RequestParam(required = false) String title) {
        List<SessionOutputDTO> sessions = sessionService
                .findTop1000ByDateAndNumberAndTitle(date, number, title);
        return ResponseEntity.ok(sessions);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{uuid}")
    public ResponseEntity<?> findById(@PathVariable UUID uuid) {
        SessionOutputDTO sessionFound = sessionService.findById(uuid);
        return ResponseEntity.ok(sessionFound);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody SessionInputDTO session) {
        session.setUuid(null);
        SessionOutputDTO sessionSaved = sessionService.save(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionSaved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(
            @PathVariable UUID uuid,
            @Valid @RequestBody SessionInputDTO session) {
        session.setUuid(uuid);
        SessionOutputDTO sessionUpdated = sessionService.update(session);
        return ResponseEntity.ok(sessionUpdated);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{uuid}/close")
    public ResponseEntity<?> close(@PathVariable UUID uuid) {
        sessionService.close(uuid);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{uuid}/open")
    public ResponseEntity<?> open(@PathVariable UUID uuid) {
        sessionService.open(uuid);
        return ResponseEntity.ok().build();
    }
}
