package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.ReservationInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.ReservationOutputDTO;
import io.github.fgabrielbraga.CineDev.service.ReservationService;
import io.github.fgabrielbraga.CineDev.service.SessionService;
import io.github.fgabrielbraga.CineDev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<ReservationOutputDTO> reservations = reservationService.findAll();
        return ResponseEntity.ok(reservations);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('CLIENT')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReservationInputDTO reservation) {
        UUID userId = reservation.getUser().getUuid();
        UUID sessionId = reservation.getSession().getUuid();
        Optional<?> userOpt = userService.findById(userId);
        Optional<?> sessionOpt = sessionService.findById(sessionId);
        if(userOpt.isPresent() && sessionOpt.isPresent()) {
            ReservationOutputDTO reservationSaved = reservationService.save(reservation);
            return ResponseEntity.status(HttpStatus.CREATED).body(reservationSaved);
        }
        return ResponseEntity.badRequest().build();
    }
}
