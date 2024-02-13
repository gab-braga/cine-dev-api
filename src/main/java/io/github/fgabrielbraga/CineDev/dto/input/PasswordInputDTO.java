package io.github.fgabrielbraga.CineDev.dto.input;

import jakarta.validation.constraints.NotBlank;

public class PasswordInputDTO {

    @NotBlank(message = "A senha atual é obrigatória.")
    private String currentPassword;
    @NotBlank(message = "A nova senha é obrigatória.")
    private String newPassword;
    @NotBlank(message = "A senha de confirmação é obrigatória.")
    private String confirmPassword;

    public PasswordInputDTO() {
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
