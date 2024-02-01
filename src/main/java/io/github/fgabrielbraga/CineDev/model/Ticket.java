package io.github.fgabrielbraga.CineDev.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
    @Column(nullable = false)
    private Boolean gap;
    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public Ticket() {
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

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(getUuid(), ticket.getUuid()) &&
                getGap() == ticket.getGap() &&
                Objects.equals(getSeat(), ticket.getSeat()) &&
                Objects.equals(getSession(), ticket.getSession()) &&
                Objects.equals(getReservation(), ticket.getReservation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(),
                getGap(),
                getSeat(),
                getSession(),
                getReservation());
    }
}

