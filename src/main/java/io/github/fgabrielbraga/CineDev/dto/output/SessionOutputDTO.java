package io.github.fgabrielbraga.CineDev.dto.output;

import io.github.fgabrielbraga.CineDev.model.Session;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

public class SessionOutputDTO {

    private UUID uuid;
    private LocalDate date;
    private LocalTime hour;
    private Boolean open;
    private Float ticketPrice;
    private Short numberFreeSeats;
    private FilmOutputDTO film;
    private RoomOutputDTO room;

    public static SessionOutputDTO ofSession(Session sessionObj) {
        return Optional.ofNullable(sessionObj).map(session -> {
            SessionOutputDTO sessionOutputDTO = new SessionOutputDTO();
            sessionOutputDTO.setUuid(session.getUuid());
            sessionOutputDTO.setDate(session.getDate());
            sessionOutputDTO.setHour(session.getHour());
            sessionOutputDTO.setOpen(session.getOpen());
            sessionOutputDTO.setTicketPrice(session.getTicketPrice());
            sessionOutputDTO.setNumberFreeSeats(session.getNumberFreeSeats());
            sessionOutputDTO.setFilm(FilmOutputDTO.ofFilm(session.getFilm()));
            sessionOutputDTO.setRoom(RoomOutputDTO.ofRoom(session.getRoom()));
            return sessionOutputDTO;
        }).orElse(null);
    }

    public SessionOutputDTO() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Short getNumberFreeSeats() {
        return numberFreeSeats;
    }

    public void setNumberFreeSeats(Short numberFreeSeats) {
        this.numberFreeSeats = numberFreeSeats;
    }

    public FilmOutputDTO getFilm() {
        return film;
    }

    public void setFilm(FilmOutputDTO film) {
        this.film = film;
    }

    public RoomOutputDTO getRoom() {
        return room;
    }

    public void setRoom(RoomOutputDTO room) {
        this.room = room;
    }
}
