package io.github.fgabrielbraga.CineDev.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
    @Column(name = "date_session", nullable = false)
    private LocalDate date;
    @Column(name = "hour_session", nullable = false)
    private LocalTime hour;
    @Column(nullable = false)
    private Boolean open;
    @Column(name = "ticket_price", nullable = false)
    private Float ticketPrice;
    @Column(name = "number_free_seats",nullable = false)
    private Short numberFreeSeats;
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

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
}
