package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.RoomInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.RoomOutputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.SessionOutputDTO;
import io.github.fgabrielbraga.CineDev.service.RoomService;
import io.github.fgabrielbraga.CineDev.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private SessionService sessionService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{uuid}")
    public ResponseEntity<RoomOutputDTO> findById(@PathVariable UUID uuid) {
        Optional<RoomOutputDTO> roomOpt = roomService.findById(uuid);
        return roomOpt
                .map(roomFound -> ResponseEntity.ok(roomFound))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<RoomOutputDTO>> findAll(
            @RequestParam(required = false) Short number) {
        List<RoomOutputDTO> rooms = roomService.findAll(number);
        return ResponseEntity.ok(rooms);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/sessions/{uuid}")
    public ResponseEntity<RoomOutputDTO> findBySessionId(
            @PathVariable UUID uuid) {
        Optional<RoomOutputDTO> roomOpt = roomService.findBySessionId(uuid);
        return roomOpt
                .map(roomFound -> ResponseEntity.ok(roomFound))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<RoomOutputDTO> create(@RequestBody RoomInputDTO room) {
        RoomOutputDTO roomSaved = roomService.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(roomSaved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{uuid}")
    public ResponseEntity<RoomOutputDTO> updateDetails(@PathVariable UUID uuid, @RequestBody RoomInputDTO room) {
        Optional<RoomOutputDTO> roomOptional = roomService.findById(uuid);
        return roomOptional
                .map(roomFound -> {
                    room.setUuid(uuid);
                    RoomOutputDTO roomUpdated = roomService.updateDetails(room);
                    return ResponseEntity.ok(roomUpdated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{uuid}/map")
    public ResponseEntity<RoomOutputDTO> updateSeatMap(@PathVariable UUID uuid, @RequestBody RoomInputDTO room) {
        List<SessionOutputDTO> sessions = sessionService.findByRoomId(uuid);
        if(sessions.isEmpty()) {
            Optional<RoomOutputDTO> roomOptional = roomService.findById(uuid);
            return roomOptional
                    .map(roomFound -> {
                        room.setUuid(uuid);
                        RoomOutputDTO roomUpdated = roomService.updateSeatMap(room);
                        return ResponseEntity.ok(roomUpdated);
                    })
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid) {
        Optional<RoomOutputDTO> roomOptional = roomService.findById(uuid);
        return roomOptional
                .map(roomFound -> {
                    roomService.deleteById(uuid);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
