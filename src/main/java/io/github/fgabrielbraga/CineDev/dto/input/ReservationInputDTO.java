package io.github.fgabrielbraga.CineDev.dto.input;

import io.github.fgabrielbraga.CineDev.enums.StatusReservation;
import io.github.fgabrielbraga.CineDev.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReservationInputDTO {

    private UUID uuid;
    private LocalDateTime reservedAt;
    private StatusReservation status;
    private UserInputDTO user;
    private SessionInputDTO session;
    private List<TicketInputDTO> tickets;

    public ReservationInputDTO() {
    }

    public static Reservation parseReservation(ReservationInputDTO reservationInputDTO) {
        return Optional.ofNullable(reservationInputDTO).map(dto -> {
            Reservation reservation = new Reservation();
            reservation.setUuid(dto.getUuid());
            reservation.setReservedAt(dto.getReservedAt());
            reservation.setUser(UserInputDTO.parseUser(dto.getUser()));
            reservation.setSession(SessionInputDTO.parseSession(dto.getSession()));
            reservation.setTickets(TicketInputDTO.toTicketList(dto.getTickets()));
            return reservation;
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

    public UserInputDTO getUser() {
        return user;
    }

    public void setUser(UserInputDTO user) {
        this.user = user;
    }

    public SessionInputDTO getSession() {
        return session;
    }

    public void setSession(SessionInputDTO session) {
        this.session = session;
    }

    public List<TicketInputDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketInputDTO> tickets) {
        this.tickets = tickets;
    }
}

