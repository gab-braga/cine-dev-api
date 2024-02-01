package io.github.fgabrielbraga.CineDev.dto.input;

import io.github.fgabrielbraga.CineDev.model.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TicketInputDTO {

    private UUID uuid;
    private Boolean gap;
    private SeatInputDTO seat;
    private SessionInputDTO session;
    private ReservationInputDTO reservation;

    public TicketInputDTO() {
    }

    public static Ticket parseTicket(TicketInputDTO ticketInputDTO) {
        return Optional.ofNullable(ticketInputDTO).map(dto -> {
            Ticket ticket = new Ticket();
            ticket.setUuid(dto.getUuid());
            ticket.setGap(dto.getGap());
            ticket.setSeat(SeatInputDTO.parseSeat(dto.getSeat()));
            ticket.setSession(SessionInputDTO.parseSession(dto.getSession()));
            ticket.setReservation(ReservationInputDTO.parseReservation(dto.getReservation()));
            return ticket;
        }).orElse(null);
    }

    public static List<Ticket> toTicketList(List<TicketInputDTO> tickets) {
        return Optional.ofNullable(tickets).map(list -> {
            return list.stream().map(ticket -> parseTicket(ticket)).toList();
        }).orElse(null);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Boolean getGap() {
        return gap;
    }

    public void setGap(Boolean gap) {
        this.gap = gap;
    }

    public SeatInputDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatInputDTO seat) {
        this.seat = seat;
    }

    public SessionInputDTO getSession() {
        return session;
    }

    public void setSession(SessionInputDTO session) {
        this.session = session;
    }

    public ReservationInputDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationInputDTO reservation) {
        this.reservation = reservation;
    }
}

