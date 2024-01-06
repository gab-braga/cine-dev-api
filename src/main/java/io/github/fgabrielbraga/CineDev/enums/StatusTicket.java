package io.github.fgabrielbraga.CineDev.enums;

public enum StatusTicket {
    FREE("Free"),
    OCCUPIED("Occupied"),
    BANNED("Banned");

    private String status;

    StatusTicket(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
