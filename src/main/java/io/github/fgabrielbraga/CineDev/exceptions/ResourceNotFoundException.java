package io.github.fgabrielbraga.CineDev.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        this("Desculpe, recurso não encontrado. Tente novamente.");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
