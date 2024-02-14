package io.github.fgabrielbraga.CineDev.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
    @Column(name = "session_date", nullable = false)
    private LocalDate date;
    @Column(name = "session_hour", nullable = false)
    private LocalTime hour;
    @Column(nullable = false)
    private Boolean open;
    @Column(name = "ticket_price", nullable = false)
    private Float ticketPrice;
    @Column(name = "number_free_seats",nullable = false)
    private Short numberFreeSeats;
    @ManyToOne
    @JoinColumn(name = "film_uuid", nullable = false)
    private Film film;
    @ManyToOne
    @JoinColumn(name = "room_uuid", nullable = false)
    private Room room;
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.open = false;
    }

    public Session() {
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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(getUuid(), session.getUuid()) &&
                Objects.equals(getDate(), session.getDate()) &&
                Objects.equals(getHour(), session.getHour()) &&
                Objects.equals(getOpen(), session.getOpen()) &&
                Objects.equals(getTicketPrice(), session.getTicketPrice()) &&
                Objects.equals(getNumberFreeSeats(), session.getNumberFreeSeats()) &&
                Objects.equals(getFilm(), session.getFilm()) &&
                Objects.equals(getRoom(), session.getRoom()) &&
                Objects.equals(getTickets(), session.getTickets());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(),
                getDate(),
                getHour(),
                getOpen(),
                getTicketPrice(),
                getNumberFreeSeats(),
                getFilm(),
                getRoom(),
                getTickets());
    }
}
