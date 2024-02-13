package io.github.fgabrielbraga.CineDev.model;

import io.github.fgabrielbraga.CineDev.enums.StatusReservation;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
    @Column(name = "reserved_at", nullable = false)
    private LocalDateTime reservedAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_reservation", nullable = false)
    private StatusReservation status;
    @ManyToOne
    @JoinColumn(name = "user_uuid", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "session_uuid", nullable = false)
    private Session session;
    @OneToMany(mappedBy = "reservation")
    private List<Ticket> tickets = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.uuid = null;
        this.reservedAt = LocalDateTime.now();
        this.status = StatusReservation.PENDING;
    }

    public Reservation() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
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
        Reservation that = (Reservation) o;
        return Objects.equals(getUuid(), that.getUuid()) &&
                Objects.equals(getReservedAt(), that.getReservedAt()) &&
                getStatus() == that.getStatus() &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getSession(), that.getSession()) &&
                Objects.equals(getTickets(), that.getTickets());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(),
                getReservedAt(),
                getStatus(),
                getUser(),
                getSession(),
                getTickets());
    }
}

