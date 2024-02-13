package io.github.fgabrielbraga.CineDev.dto.output;

import org.springframework.http.HttpStatus;

public class ExceptionOutputDTO {

    private HttpStatus code = HttpStatus.BAD_REQUEST;
    private String status = HttpStatus.BAD_REQUEST.getReasonPhrase();
    private String message = "Pedido inválido. Por favor, verifique seus dados e tente novamente.";

    public ExceptionOutputDTO() {
    }

    public ExceptionOutputDTO(String message) {
        this.message = message;
    }

    public ExceptionOutputDTO(HttpStatus code, String message) {
        this.code = code;
        this.status = code.getReasonPhrase();
        this.message = message;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
