package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.output.MapOutputDTO;
import io.github.fgabrielbraga.CineDev.service.MapService;
import io.github.fgabrielbraga.CineDev.service.RoomService;
import io.github.fgabrielbraga.CineDev.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/maps")
public class MapController {

    @Autowired
    private MapService mapService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private SessionService sessionService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/rooms/{uuid}")
    public ResponseEntity<?> findByRoomId(@PathVariable UUID uuid) {
        Optional<?> roomOpt = roomService.findById(uuid);
        return roomOpt.map((room) -> {
            Optional<MapOutputDTO> mapOpt = mapService.findByRoomId(uuid);
            return mapOpt
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }).orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('CLIENT')")
    @GetMapping("/sessions/{uuid}")
    public ResponseEntity<?> findBySessionId(@PathVariable UUID uuid) {
        Optional<?> sessionOpt = sessionService.findById(uuid);
        return sessionOpt.map((session) -> {
            Optional<MapOutputDTO> mapOpt = mapService.findBySessionId(uuid);
            return mapOpt
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
