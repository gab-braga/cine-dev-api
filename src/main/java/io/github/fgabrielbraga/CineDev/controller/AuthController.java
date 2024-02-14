package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.CredentialsInputDTO;
import io.github.fgabrielbraga.CineDev.dto.input.UserInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.TokenOutputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.UserOutputDTO;
import io.github.fgabrielbraga.CineDev.service.AuthService;
import io.github.fgabrielbraga.CineDev.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenOutputDTO> login(
            @Valid @RequestBody CredentialsInputDTO credentials) {
        TokenOutputDTO token = authService.login(credentials);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserOutputDTO> signup(
            @Valid @RequestBody UserInputDTO userInputDTO) {
        UserOutputDTO userSaved = userService.save(userInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }
}
