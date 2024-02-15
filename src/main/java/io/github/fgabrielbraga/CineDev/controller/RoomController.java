package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.RoomInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.RoomOutputDTO;
import io.github.fgabrielbraga.CineDev.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(required = false) Short number) {
        List<RoomOutputDTO> rooms = roomService.findTop1000ByNumber(number);
        return ResponseEntity.ok(rooms);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{uuid}")
    public ResponseEntity<?> findById(@PathVariable UUID uuid) {
        RoomOutputDTO roomFound = roomService.findById(uuid);
        return ResponseEntity.ok(roomFound);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody RoomInputDTO room) {
        RoomOutputDTO roomSaved = roomService.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(roomSaved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateDetails(
            @PathVariable UUID uuid,
            @RequestBody RoomInputDTO room) {
        room.setUuid(uuid);
        RoomOutputDTO roomUpdated = roomService.updateDetails(room);
        return ResponseEntity.ok(roomUpdated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{uuid}/maps")
    public ResponseEntity<?> updateSeatMap(
            @PathVariable UUID uuid,
            @RequestBody RoomInputDTO room) {
        room.setUuid(uuid);
        RoomOutputDTO roomUpdated = roomService.updateSeatMap(room);
        return ResponseEntity.ok(roomUpdated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid) {
        roomService.deleteById(uuid);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('CLIENT')")
    @GetMapping("/sessions/{uuid}")
    public ResponseEntity<?> findBySessionId(@PathVariable UUID uuid) {
        RoomOutputDTO roomFound = roomService.findBySessionId(uuid);
        return ResponseEntity.ok(roomFound);
    }
}
