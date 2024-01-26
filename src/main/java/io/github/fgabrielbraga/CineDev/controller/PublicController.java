package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.output.SessionOutputDTO;
import io.github.fgabrielbraga.CineDev.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private SessionService sessionService;

    @GetMapping("/sessions")
    public ResponseEntity<List<SessionOutputDTO>> findNearby() {
        List<SessionOutputDTO> sessions = sessionService.findNearby();
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/sessions/week")
    public ResponseEntity<List<SessionOutputDTO>> findAllThisWeek() {
        List<SessionOutputDTO> sessions = sessionService.findAllThisWeek();
        return ResponseEntity.ok(sessions);
    }
}
