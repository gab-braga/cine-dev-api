package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.RoomInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.RoomOutputDTO;
import io.github.fgabrielbraga.CineDev.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/{uuid}")
    public ResponseEntity<RoomOutputDTO> findById(@PathVariable UUID uuid) {
        Optional<RoomOutputDTO> roomOptional = roomService.findById(uuid);
        return roomOptional
                .map(roomFound -> ResponseEntity.ok(roomFound))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<RoomOutputDTO>> findAll(
            @RequestParam(required = false) Short number) {
        List<RoomOutputDTO> rooms = roomService.findAll(number);
        return ResponseEntity.ok(rooms);
    }

    @PostMapping
    public ResponseEntity<RoomOutputDTO> create(@RequestBody RoomInputDTO room) {
        room.setUuid(null);
        RoomOutputDTO roomSaved = roomService.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(roomSaved);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<RoomOutputDTO> update(@PathVariable UUID uuid, @RequestBody RoomInputDTO room) {
        Optional<RoomOutputDTO> roomOptional = roomService.findById(uuid);
        return roomOptional
                .map(roomFound -> {
                    room.setUuid(uuid);
                    RoomOutputDTO roomUpdated = roomService.save(room);
                    return ResponseEntity.ok(roomUpdated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

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
