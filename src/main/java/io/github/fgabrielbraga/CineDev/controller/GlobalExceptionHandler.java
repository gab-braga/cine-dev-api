package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.output.ExceptionOutputDTO;
import io.github.fgabrielbraga.CineDev.exceptions.InvalidParameterException;
import io.github.fgabrielbraga.CineDev.exceptions.ResourceNotFoundException;
import io.github.fgabrielbraga.CineDev.exceptions.ResourceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionOutputDTO> resourceNotFoundException(
            ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionOutputDTO(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(ResourceUnavailableException.class)
    public ResponseEntity<ExceptionOutputDTO> resourceUnavailableException(
            ResourceUnavailableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionOutputDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ExceptionOutputDTO> invalidParameterException(
            InvalidParameterException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionOutputDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionOutputDTO> usernameNotFoundException(
            UsernameNotFoundException e) {
        String message = "Desculpe, o e-mail inserido não está cadastrado. Por favor, tente novamente.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionOutputDTO(HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionOutputDTO> badCredentialsException(
            BadCredentialsException e) {
        String message = "Desculpe, o e-mail ou a senha inseridos estão incorretos. Por favor, tente novamente.";
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ExceptionOutputDTO(HttpStatus.FORBIDDEN, message));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionOutputDTO> accessDeniedException(
            AccessDeniedException e) {
        String message = "Acesso negado. Você não possui permissão para acessar este recurso.";
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ExceptionOutputDTO(HttpStatus.FORBIDDEN, message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionOutputDTO> methodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        List<FieldError> erros = e.getBindingResult().getFieldErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionOutputDTO(HttpStatus.BAD_REQUEST, erros.get(0).getDefaultMessage()));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionOutputDTO> bindException(
            BindException e) {
        List<FieldError> erros = e.getBindingResult().getFieldErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionOutputDTO(HttpStatus.BAD_REQUEST, erros.get(0).getDefaultMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionOutputDTO> exception(Exception e) {
        String message = "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionOutputDTO(HttpStatus.INTERNAL_SERVER_ERROR, message));
    }
}
