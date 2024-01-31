package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.output.TicketOutputDTO;
import io.github.fgabrielbraga.CineDev.model.Ticket;
import io.github.fgabrielbraga.CineDev.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<TicketOutputDTO> findBySessionId(UUID uuid) {
        List<Ticket> tickets = ticketRepository.findBySessionId(uuid);
        return tickets.stream().map(TicketOutputDTO::ofTicket).toList();
    }
}
