package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.input.TicketInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.TicketOutputDTO;
import io.github.fgabrielbraga.CineDev.model.Ticket;
import io.github.fgabrielbraga.CineDev.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Optional<TicketOutputDTO> findById(UUID uuid) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(uuid);
        return ticketOpt.map(TicketOutputDTO::ofTicket);
    }

    public List<TicketOutputDTO> findAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(TicketOutputDTO::ofTicket).toList();
    }

    public TicketOutputDTO save(TicketInputDTO ticketInputDTO) {
        Ticket ticket = TicketInputDTO.parseTicket(ticketInputDTO);
        Ticket ticketSaved = ticketRepository.save(ticket);
        return TicketOutputDTO.ofTicket(ticketSaved);
    }

    public void deleteById(UUID uuid) {
        ticketRepository.deleteById(uuid);
    }
}
