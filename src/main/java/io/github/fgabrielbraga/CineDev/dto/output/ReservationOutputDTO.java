package io.github.fgabrielbraga.CineDev.dto.output;

import io.github.fgabrielbraga.CineDev.enums.StatusReservation;
import io.github.fgabrielbraga.CineDev.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReservationOutputDTO {

    private UUID uuid;
    private LocalDateTime reservedAt;
    private StatusReservation status;
    private UserOutputDTO user;
    private SessionOutputDTO session;
    private List<TicketOutputDTO> tickets;

    public ReservationOutputDTO() {
    }

    public static ReservationOutputDTO ofReservation(Reservation reservationObj) {
        return Optional.ofNullable(reservationObj).map(reservation -> {
            ReservationOutputDTO reservationOutputDTO = new ReservationOutputDTO();
            reservationOutputDTO.setUuid(reservation.getUuid());
            reservationOutputDTO.setReservedAt(reservation.getReservedAt());
            reservationOutputDTO.setUser(UserOutputDTO.ofUser(reservation.getUser()));
            reservationOutputDTO.setSession(SessionOutputDTO.ofSession(reservation.getSession()));
            reservationOutputDTO.setTickets(TicketOutputDTO.toTicketOutputDTOList(reservation.getTickets()));
            return reservationOutputDTO;
        }).orElse(null);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }

    public StatusReservation getStatus() {
        return status;
    }

    public void setStatus(StatusReservation status) {
        this.status = status;
    }

    public UserOutputDTO getUser() {
        return user;
    }

    public void setUser(UserOutputDTO user) {
        this.user = user;
    }

    public SessionOutputDTO getSession() {
        return session;
    }

    public void setSession(SessionOutputDTO session) {
        this.session = session;
    }

    public List<TicketOutputDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketOutputDTO> tickets) {
        this.tickets = tickets;
    }
}

