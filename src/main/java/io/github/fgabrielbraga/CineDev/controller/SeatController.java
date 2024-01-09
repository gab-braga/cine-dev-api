package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.SeatInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.SeatOutputDTO;
import io.github.fgabrielbraga.CineDev.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/{uuid}")
    public ResponseEntity<SeatOutputDTO> findById(@PathVariable UUID uuid) {
        Optional<SeatOutputDTO> seatOptional = seatService.findById(uuid);
        return seatOptional
                .map(seatFound -> ResponseEntity.ok(seatFound))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SeatOutputDTO>> findAll() {
        List<SeatOutputDTO> seats = seatService.findAll();
        return ResponseEntity.ok(seats);
    }

    @PostMapping
    public ResponseEntity<SeatOutputDTO> create(@RequestBody SeatInputDTO seat) {
        seat.setUuid(null);
        SeatOutputDTO seatSaved = seatService.save(seat);
        return ResponseEntity.status(HttpStatus.CREATED).body(seatSaved);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<SeatOutputDTO> update(@PathVariable UUID uuid, @RequestBody SeatInputDTO seat) {
        Optional<SeatOutputDTO> seatOptional = seatService.findById(uuid);
        return seatOptional
                .map(seatFound -> {
                    seat.setUuid(uuid);
                    SeatOutputDTO seatUpdated = seatService.save(seat);
                    return ResponseEntity.ok(seatUpdated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid) {
        Optional<SeatOutputDTO> seatOptional = seatService.findById(uuid);
        return seatOptional
                .map(seatFound -> {
                    seatService.deleteById(uuid);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
