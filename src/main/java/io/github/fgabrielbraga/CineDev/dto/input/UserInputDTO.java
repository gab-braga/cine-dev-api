package io.github.fgabrielbraga.CineDev.dto.input;

import io.github.fgabrielbraga.CineDev.model.User;
import io.github.fgabrielbraga.CineDev.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class UserInputDTO {

    private UUID uuid;
    @NotBlank(message = "O nome do usuário é obrigatório.")
    @Size(max = 120, message = "O número de caracteres do nome excede o limite.")
    private String name;
    @Email(message = "O e-mail do usuário está inválido.")
    @NotBlank(message = "O e-mail do usuário é obrigatório.")
    @Size(max = 150, message = "O número de caracteres do e-mail excede o limite.")
    private String email;
    @NotBlank(message = "A senha do usuário é obrigatória.")
    private String password;
    @CPF(message = "O CPF do usuário está inválido.")
    @NotBlank(message = "O CPF do usuário é obrigatório.")
    private String cpf;
    @Size(max = 16, message = "O número de caracteres do telefone excede o limite.")
    private String phoneNumber;
    private String profilePicture;
    private Boolean active;
    private Boolean confirmed;
    private Role role = Role.CLIENT;
    private LocalDateTime createdAt;

    public UserInputDTO() {
    }

    public static User parseUser(UserInputDTO userDTO) {
        return Optional.ofNullable(userDTO).map(dto -> {
            User user = new User();
            user.setUuid(dto.getUuid());
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            user.setCpf(dto.getCpf());
            user.setPhoneNumber(dto.getPhoneNumber());
            user.setProfilePicture(dto.getProfilePicture());
            user.setActive(dto.getActive());
            user.setConfirmed(dto.getConfirmed());
            user.setRole(dto.getRole());
            user.setCreatedAt(dto.getCreatedAt());
            return user;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
