package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.CredentialsInputDTO;
import io.github.fgabrielbraga.CineDev.dto.input.UserInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.TokenOutputDTO;
import io.github.fgabrielbraga.CineDev.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenOutputDTO> login(@RequestBody CredentialsInputDTO credentials) {
        TokenOutputDTO token = authService.login(credentials);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public void signup(UserInputDTO userInputDTO) {
        System.out.println(userInputDTO.getEmail());
    }
}
