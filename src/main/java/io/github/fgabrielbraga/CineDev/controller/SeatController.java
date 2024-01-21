package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.SeatInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.SeatOutputDTO;
import io.github.fgabrielbraga.CineDev.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/room/{uuid}")
    public ResponseEntity<List<SeatOutputDTO>> findByRoomId(@PathVariable UUID uuid) {
        List<SeatOutputDTO> seats = seatService.findByRoomId(uuid);
        return ResponseEntity.ok(seats);
    }
}
