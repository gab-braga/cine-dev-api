package io.github.fgabrielbraga.CineDev.dto.input;

public class CredentialsInputDTO {

    private String email;
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
