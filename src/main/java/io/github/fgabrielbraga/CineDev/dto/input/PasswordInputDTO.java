package io.github.fgabrielbraga.CineDev.dto.input;

import jakarta.validation.constraints.NotBlank;

public class PasswordInputDTO {

    @NotBlank(message = "Por favor, forneça a senha atual.")
    private String currentPassword;
    @NotBlank(message = "Por favor, forneça a nova senha.")
    private String newPassword;
    @NotBlank(message = "Por favor, forneça a confirmação da nova senha.")
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
