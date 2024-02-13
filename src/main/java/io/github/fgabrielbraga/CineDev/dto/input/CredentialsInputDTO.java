package io.github.fgabrielbraga.CineDev.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CredentialsInputDTO {

    @Email(message = "O e-mail está inválido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;
    @NotBlank(message = "A senha é obrigatória.")
    private String password;

    public CredentialsInputDTO() {}

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
}
