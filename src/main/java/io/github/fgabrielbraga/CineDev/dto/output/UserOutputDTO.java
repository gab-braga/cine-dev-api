package io.github.fgabrielbraga.CineDev.dto.output;

import io.github.fgabrielbraga.CineDev.enums.Role;
import io.github.fgabrielbraga.CineDev.model.User;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class UserOutputDTO {

    private UUID uuid;
    private String name;
    private String email;
    private String cpf;
    private String phoneNumber;
    private String profilePicture;
    private Boolean active;
    private Boolean confirmed;
    private Role role = Role.CLIENT;
    private LocalDateTime createdAt;

    public UserOutputDTO() {
    }

    public static UserOutputDTO ofUser(User userObj) {
        return Optional.ofNullable(userObj).map(user -> {
            UserOutputDTO userOutputDTO = new UserOutputDTO();
            userOutputDTO.uuid = user.getUuid();
            userOutputDTO.name = user.getName();
            userOutputDTO.email = user.getEmail();
            userOutputDTO.cpf = user.getCpf();
            userOutputDTO.phoneNumber = user.getPhoneNumber();
            userOutputDTO.profilePicture = user.getProfilePicture();
            userOutputDTO.active = user.getActive();
            userOutputDTO.confirmed = user.getConfirmed();
            userOutputDTO.role = user.getRole();
            userOutputDTO.createdAt = user.getCreatedAt();
            return userOutputDTO;
        }).orElse(null);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
