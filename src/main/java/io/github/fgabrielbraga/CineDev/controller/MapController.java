package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.output.MapOutputDTO;
import io.github.fgabrielbraga.CineDev.service.MapService;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/rooms/{uuid}")
    public ResponseEntity<?> findByRoomId(@PathVariable UUID uuid) {
        Optional<MapOutputDTO> mapOpt = mapService.findByRoomId(uuid);
        return mapOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/sessions/{uuid}")
    public ResponseEntity<?> findBySessionId(@PathVariable UUID uuid) {
        Optional<MapOutputDTO> mapOpt = mapService.findBySesionId(uuid);
        return mapOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
