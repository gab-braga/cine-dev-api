package io.github.fgabrielbraga.CineDev.model.enums;

public enum StatusReservation {
    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    CANCELED("Canceled");

    private String status;

    StatusReservation(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
