package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.output.AreaOutputDTO;
import io.github.fgabrielbraga.CineDev.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/room/{uuid}")
    public ResponseEntity<List<AreaOutputDTO>> findByRoomId(@PathVariable UUID uuid) {
        List<AreaOutputDTO> seats = areaService.findByRoomId(uuid);
        return ResponseEntity.ok(seats);
    }
}
