package io.github.fgabrielbraga.CineDev.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CredentialsInputDTO {

    @NotBlank(message = "Por favor, forneça um endereço de e-mail.")
    @Email(message = "O formato do e-mail é inválido. Certifique-se de que está correto.")
    private String email;
    @NotBlank(message = "Por favor, forneça uma senha.")
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
