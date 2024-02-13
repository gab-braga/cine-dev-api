package io.github.fgabrielbraga.CineDev.dto.output;

import org.springframework.http.HttpStatus;

public class ExceptionOutputDTO {

    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private String statusText = HttpStatus.BAD_REQUEST.getReasonPhrase();
    private String message = "Pedido inválido. Por favor, verifique seus dados e tente novamente.";

    public ExceptionOutputDTO() {
    }

    public ExceptionOutputDTO(String message) {
        this.message = message;
    }

    public ExceptionOutputDTO(HttpStatus status, String message) {
        this.status = status;
        this.statusText = status.getReasonPhrase();
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
