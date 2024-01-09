package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.TicketInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.TicketOutputDTO;
import io.github.fgabrielbraga.CineDev.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{uuid}")
    public ResponseEntity<TicketOutputDTO> findById(@PathVariable UUID uuid) {
        Optional<TicketOutputDTO> ticketOptional = ticketService.findById(uuid);
        return ticketOptional
                .map(ticketFound -> ResponseEntity.ok(ticketFound))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TicketOutputDTO>> findAll() {
        List<TicketOutputDTO> tickets = ticketService.findAll();
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    public ResponseEntity<TicketOutputDTO> create(@RequestBody TicketInputDTO ticket) {
        ticket.setUuid(null);
        TicketOutputDTO ticketSaved = ticketService.save(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketSaved);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<TicketOutputDTO> update(@PathVariable UUID uuid, @RequestBody TicketInputDTO ticket) {
        Optional<TicketOutputDTO> ticketOptional = ticketService.findById(uuid);
        return ticketOptional
                .map(ticketFound -> {
                    ticket.setUuid(uuid);
                    TicketOutputDTO ticketUpdated = ticketService.save(ticket);
                    return ResponseEntity.ok(ticketUpdated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid) {
        Optional<TicketOutputDTO> ticketOptional = ticketService.findById(uuid);
        return ticketOptional
                .map(ticketFound -> {
                    ticketService.deleteById(uuid);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
