package io.github.fgabrielbraga.CineDev.dto.output;

import io.github.fgabrielbraga.CineDev.model.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TicketOutputDTO {

    private UUID uuid;
    private Boolean gap;
    private SeatOutputDTO seat;
    private SessionOutputDTO session;
    private ReservationOutputDTO reservation;

    public TicketOutputDTO() {
    }

    public static TicketOutputDTO ofTicket(Ticket ticketObj) {
        return Optional.ofNullable(ticketObj).map(ticket -> {
            TicketOutputDTO ticketOutputDTO = new TicketOutputDTO();
            ticketOutputDTO.setUuid(ticket.getUuid());
            ticketOutputDTO.setGap(ticket.getGap());
            ticketOutputDTO.setSeat(SeatOutputDTO.ofSeat(ticket.getSeat()));
            ticketOutputDTO.setSession(SessionOutputDTO.ofSession(ticket.getSession()));
            ticketOutputDTO.setReservation(ReservationOutputDTO.ofReservation(ticket.getReservation()));
            return ticketOutputDTO;
        }).orElse(null);
    }

    public static List<TicketOutputDTO> toTicketOutputDTOList(List<Ticket> tickets) {
        return Optional.ofNullable(tickets).map(list -> {
            return list.stream().map(ticket -> ofTicket(ticket)).toList();
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

    public SeatOutputDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatOutputDTO seat) {
        this.seat = seat;
    }

    public SessionOutputDTO getSession() {
        return session;
    }

    public void setSession(SessionOutputDTO session) {
        this.session = session;
    }

    public ReservationOutputDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationOutputDTO reservation) {
        this.reservation = reservation;
    }
}

