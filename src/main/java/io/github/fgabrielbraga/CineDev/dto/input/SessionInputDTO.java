package io.github.fgabrielbraga.CineDev.dto.input;

import io.github.fgabrielbraga.CineDev.model.Session;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

public class SessionInputDTO {

    private UUID uuid;
    @NotNull(message = "A data da sessão é obrigatória.")
    private LocalDate date;
    @NotNull(message = "O horário da sessão é obrigatório.")
    private LocalTime hour;
    private Boolean open;
    @NotNull(message = "O preço do ingresso da sessão é obrigatório.")
    private Float ticketPrice;
    private Short numberFreeSeats;
    @NotNull(message = "O filme da sessão é obrigatório.")
    private FilmInputDTO film;
    @NotNull(message = "A sala da sessão é obrigatória.")
    private RoomInputDTO room;

    public static Session parseSession(SessionInputDTO sessionInputDTO) {
        return Optional.ofNullable(sessionInputDTO).map(dto -> {
            Session session = new Session();
            session.setUuid(dto.getUuid());
            session.setDate(dto.getDate());
            session.setHour(dto.getHour());
            session.setOpen(dto.getOpen());
            session.setTicketPrice(dto.getTicketPrice());
            session.setNumberFreeSeats(dto.getNumberFreeSeats());
            session.setFilm(FilmInputDTO.parseFilm(dto.getFilm()));
            session.setRoom(RoomInputDTO.parseRoom(dto.getRoom()));
            return session;
        }).orElse(null);
    }

    public SessionInputDTO() {
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

    public FilmInputDTO getFilm() {
        return film;
    }

    public void setFilm(FilmInputDTO film) {
        this.film = film;
    }

    public RoomInputDTO getRoom() {
        return room;
    }

    public void setRoom(RoomInputDTO room) {
        this.room = room;
    }
}
