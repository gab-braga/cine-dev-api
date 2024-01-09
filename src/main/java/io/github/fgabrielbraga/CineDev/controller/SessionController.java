package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.SessionInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.SessionOutputDTO;
import io.github.fgabrielbraga.CineDev.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping("/{uuid}")
    public ResponseEntity<SessionOutputDTO> findById(@PathVariable UUID uuid) {
        Optional<SessionOutputDTO> sessionOptional = sessionService.findById(uuid);
        return sessionOptional
                .map(sessionFound -> ResponseEntity.ok(sessionFound))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SessionOutputDTO>> findAll() {
        List<SessionOutputDTO> sessions = sessionService.findAll();
        return ResponseEntity.ok(sessions);
    }

    @PostMapping
    public ResponseEntity<SessionOutputDTO> create(@RequestBody SessionInputDTO session) {
        session.setUuid(null);
        SessionOutputDTO sessionSaved = sessionService.save(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionSaved);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<SessionOutputDTO> update(@PathVariable UUID uuid, @RequestBody SessionInputDTO session) {
        Optional<SessionOutputDTO> sessionOptional = sessionService.findById(uuid);
        return sessionOptional
                .map(sessionFound -> {
                    session.setUuid(uuid);
                    SessionOutputDTO sessionUpdated = sessionService.save(session);
                    return ResponseEntity.ok(sessionUpdated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid) {
        Optional<SessionOutputDTO> sessionOptional = sessionService.findById(uuid);
        return sessionOptional
                .map(sessionFound -> {
                    sessionService.deleteById(uuid);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
