package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.ReservationInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.ReservationOutputDTO;
import io.github.fgabrielbraga.CineDev.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> findTop1000() {
        List<ReservationOutputDTO> reservations = reservationService.findTop1000();
        return ResponseEntity.ok(reservations);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('CLIENT')")
    @GetMapping
    public ResponseEntity<?> findTop1000BySessionId(@PathVariable UUID uuid) {
        List<ReservationOutputDTO> reservations = reservationService.findTop1000BySessionId(uuid);
        return ResponseEntity.ok(reservations);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('CLIENT')")
    @GetMapping
    public ResponseEntity<?> findTop1000ByUserId(@PathVariable UUID uuid) {
        List<ReservationOutputDTO> reservations = reservationService.findTop1000ByUserId(uuid);
        return ResponseEntity.ok(reservations);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('CLIENT')")
    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody ReservationInputDTO reservation) {
        ReservationOutputDTO reservationSaved = reservationService.save(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationSaved);
    }
}
