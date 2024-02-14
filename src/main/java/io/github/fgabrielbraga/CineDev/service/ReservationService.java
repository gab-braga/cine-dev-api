package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.input.ReservationInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.ReservationOutputDTO;
import io.github.fgabrielbraga.CineDev.exceptions.ResourceNotFoundException;
import io.github.fgabrielbraga.CineDev.exceptions.ResourceUnavailableException;
import io.github.fgabrielbraga.CineDev.model.*;
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

    public ReservationOutputDTO findById(UUID uuid) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(uuid);
        return reservationOpt.map(ReservationOutputDTO::ofReservation).orElseThrow(() ->
                new ResourceNotFoundException("Desculpe, reserva não encontrada. Tente novamente."));
    }

    public List<ReservationOutputDTO> findTop1000() {
        List<Reservation> reservations = reservationRepository.findTop1000();
        return reservations.stream().map(ReservationOutputDTO::ofReservation).toList();
    }

    public List<ReservationOutputDTO> findTop1000BySessionId(UUID uuid) {
        List<Reservation> reservations = reservationRepository.findTop1000BySessionId(uuid);
        return reservations.stream().map(ReservationOutputDTO::ofReservation).toList();
    }

    public List<ReservationOutputDTO> findTop1000ByUserId(UUID uuid) {
        List<Reservation> reservations = reservationRepository.findTop1000ByUserId(uuid);
        return reservations.stream().map(ReservationOutputDTO::ofReservation).toList();
    }

    public ReservationOutputDTO save(ReservationInputDTO reservationInputDTO) {
        Reservation reservation = ReservationInputDTO
                .parseReservation(reservationInputDTO);
        User user = userRepository
                .findById(reservation.getUser().getUuid()).orElseThrow(() ->
                        new ResourceNotFoundException("Desculpe, usuário não encontrado. Tente novamente."));
        Session session = sessionRepository
                .findById(reservation.getSession().getUuid()).orElseThrow(() ->
                        new ResourceNotFoundException("Desculpe, sessão não encontrada. Tente novamente."));
        reservation.setUser(user);
        reservation.setSession(session);
        if(session.getOpen()) {
            List<Ticket> tickets = reservation.getTickets();
            List<UUID> uuids = tickets.stream().map(Ticket::getUuid).toList();
            List<Ticket> ticketsFound = ticketRepository.findByIdIn(uuids);
            int countTickets = tickets.size();
            int countTicketsFound = ticketsFound.size();
            if(countTickets == countTicketsFound) {
                List<Ticket> reservedTickets = ticketsFound.stream().filter(ticket ->
                        ticket.getReservation() != null).toList();
                if(reservedTickets.isEmpty()) {
                    reservation.setTickets(ticketsFound);
                    ticketsFound.forEach(ticket -> ticket.setReservation(reservation));
                    resetIdentifier(reservation);
                    Reservation reservationSaved = reservationRepository.save(reservation);
                    return ReservationOutputDTO.ofReservation(reservationSaved);
                }
                throw new ResourceUnavailableException("Desculpe, ingressos indisponíveis. Tente novamente.");
            }
            throw new ResourceNotFoundException("Desculpe, ingressos não encontrados. Tente novamente.");
        }
        throw new ResourceUnavailableException("Desculpe, sessão indisponível. Tente novamente.");
    }

    private void resetIdentifier(Reservation reservation) {
        reservation.setUuid(null);
    }
}
