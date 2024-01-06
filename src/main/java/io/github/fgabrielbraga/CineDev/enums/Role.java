package io.github.fgabrielbraga.CineDev.enums;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    CLIENT("ROLE_CLIENT");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
