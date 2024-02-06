package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.UserInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.UserOutputDTO;
import io.github.fgabrielbraga.CineDev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        Optional<UserOutputDTO> userOptional = userService.findByEmail(email);
        return userOptional
                .map(userFound -> ResponseEntity.ok(userFound))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{uuid}")
    public ResponseEntity<?> findById(@PathVariable UUID uuid) {
        Optional<UserOutputDTO> userOptional = userService.findById(uuid);
        return userOptional
                .map(userFound -> ResponseEntity.ok(userFound))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String cpf) {
        List<UserOutputDTO> users = userService.findAll(name, email, cpf);
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserInputDTO user) {
        user.setUuid(null);
        UserOutputDTO userSaved = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid, @RequestBody UserInputDTO user) {
        Optional<UserOutputDTO> userOptional = userService.findById(uuid);
        return userOptional
                .map(userFound -> {
                    user.setUuid(uuid);
                    UserOutputDTO userUpdated = userService.update(user);
                    return ResponseEntity.ok(userUpdated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{uuid}/disable")
    public ResponseEntity<?> disable(@PathVariable UUID uuid) {
        Optional<UserOutputDTO> userOptional = userService.findById(uuid);
        return userOptional
                .map(userFound -> {
                    userService.disable(uuid);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{uuid}/enable")
    public ResponseEntity<?> enable(@PathVariable UUID uuid) {
        Optional<UserOutputDTO> userOptional = userService.findById(uuid);
        return userOptional
                .map(userFound -> {
                    userService.enable(uuid);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
