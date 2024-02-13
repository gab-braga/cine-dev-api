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
    @ManyToOne
    @JoinColumn(name = "area_uuid", nullable = false)
    private Area area;
    @ManyToOne
    @JoinColumn(name = "session_uuid", nullable = false)
    private Session session;
    @ManyToOne
    @JoinColumn(name = "reservation_uuid")
    private Reservation reservation;

    @PrePersist
    protected void onCreate() {
        this.uuid = null;
    }

    public Ticket() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
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
                Objects.equals(getArea(), ticket.getArea()) &&
                Objects.equals(getSession(), ticket.getSession()) &&
                Objects.equals(getReservation(), ticket.getReservation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(),
                getArea(),
                getSession(),
                getReservation());
    }
}

