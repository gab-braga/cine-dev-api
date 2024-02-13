package io.github.fgabrielbraga.CineDev.exceptions;

public class ResourceUnavailableException extends RuntimeException {

    public ResourceUnavailableException() {
        this("Desculpe, recurso indisponível. Tente novamente.");
    }

    public ResourceUnavailableException(String message) {
        super(message);
    }
}
