package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.input.ReservationInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.ReservationOutputDTO;
import io.github.fgabrielbraga.CineDev.model.Reservation;
import io.github.fgabrielbraga.CineDev.model.Session;
import io.github.fgabrielbraga.CineDev.model.Ticket;
import io.github.fgabrielbraga.CineDev.model.User;
import io.github.fgabrielbraga.CineDev.repository.ReservationRepository;
import io.github.fgabrielbraga.CineDev.repository.SessionRepository;
import io.github.fgabrielbraga.CineDev.repository.TicketRepository;
import io.github.fgabrielbraga.CineDev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public Optional<ReservationOutputDTO> findById(UUID uuid) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(uuid);
        return reservationOpt.map(ReservationOutputDTO::ofReservation);
    }

    public List<ReservationOutputDTO> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(ReservationOutputDTO::ofReservation).toList();
    }

    public List<ReservationOutputDTO> findBySessionId(UUID uuid) {
        List<Reservation> reservations = reservationRepository.findBySessionId(uuid);
        return reservations.stream().map(ReservationOutputDTO::ofReservation).toList();
    }

    public ReservationOutputDTO save(ReservationInputDTO reservationInputDTO) {
        Reservation reservation = ReservationInputDTO
                .parseReservation(reservationInputDTO);
        User user = userRepository
                .findById(reservation.getUser().getUuid()).orElseThrow();
        Session session = sessionRepository
                .findById(reservation.getUser().getUuid()).orElseThrow();
        reservation.setUser(user);
        reservation.setSession(session);
        if(session.getOpen()) {
            List<Ticket> tickets = reservation.getTickets();
            List<UUID> uuids = tickets.stream().map(Ticket::getUuid).toList();
            List<Ticket> ticketsFound = ticketRepository.findByIdIn(uuids);
            int countTickets = tickets.size();
            int countTicketsExistence = ticketsFound.size();
            if(countTickets == countTicketsExistence) {
                reservation.setTickets(ticketsFound);
                ticketsFound.forEach(ticket -> ticket.setReservation(reservation));
                Reservation reservationSaved = reservationRepository.save(reservation);
                return ReservationOutputDTO.ofReservation(reservationSaved);
            }
            throw new RuntimeException("Tickets not found.");
        }
        throw new RuntimeException("Session is closed.");
    }

    public void deleteById(UUID uuid) {
        reservationRepository.deleteById(uuid);
    }
}
