package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.ReservationInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.ReservationOutputDTO;
import io.github.fgabrielbraga.CineDev.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/{uuid}")
    public ResponseEntity<?> findById(@PathVariable UUID uuid) {
        Optional<ReservationOutputDTO> reservationOptional = reservationService.findById(uuid);
        return reservationOptional
                .map(reservationFound -> ResponseEntity.ok(reservationFound))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<ReservationOutputDTO> reservations = reservationService.findAll();
        return ResponseEntity.ok(reservations);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReservationInputDTO reservation) {
        reservation.setUuid(null);
        ReservationOutputDTO reservationSaved = reservationService.save(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationSaved);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid, @RequestBody ReservationInputDTO reservation) {
        Optional<ReservationOutputDTO> reservationOptional = reservationService.findById(uuid);
        return reservationOptional
                .map(reservationFound -> {
                    reservation.setUuid(uuid);
                    ReservationOutputDTO reservationUpdated = reservationService.save(reservation);
                    return ResponseEntity.ok(reservationUpdated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid) {
        Optional<ReservationOutputDTO> reservationOptional = reservationService.findById(uuid);
        return reservationOptional
                .map(reservationFound -> {
                    reservationService.deleteById(uuid);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
