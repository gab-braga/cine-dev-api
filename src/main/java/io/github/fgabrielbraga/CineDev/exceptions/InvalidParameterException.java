package io.github.fgabrielbraga.CineDev.exceptions;

public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException() {
        this("Desculpe, argumento não é válido. Certifique-se de inserir um valor correto e tente novamente.");
    }

    public InvalidParameterException(String message) {
        super(message);
    }
}
