package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.PasswordInputDTO;
import io.github.fgabrielbraga.CineDev.dto.input.UserInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.UserOutputDTO;
import io.github.fgabrielbraga.CineDev.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> findTop1000ByNameAndEmailAndCpf(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String cpf) {
        List<UserOutputDTO> users = userService
                .findTop1000ByNameAndEmailAndCpf(name, email, cpf);
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody UserInputDTO user) {
        UserOutputDTO userSaved = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(
            @PathVariable UUID uuid,
            @RequestBody UserInputDTO user) {
        user.setUuid(uuid);
        UserOutputDTO userUpdated = userService.update(user);
        return ResponseEntity.ok(userUpdated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{uuid}/disable")
    public ResponseEntity<?> disable(@PathVariable UUID uuid) {
        userService.disable(uuid);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{uuid}/enable")
    public ResponseEntity<?> enable(@PathVariable UUID uuid) {
        userService.enable(uuid);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('CLIENT')")
    @GetMapping("/{uuid}")
    public ResponseEntity<?> findById(@PathVariable UUID uuid) {
        UserOutputDTO userFound = userService.findById(uuid);
        return ResponseEntity.ok(userFound);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('CLIENT')")
    @PutMapping("/{uuid}/profile")
    public ResponseEntity<?> updateProfile(
            @PathVariable UUID uuid,
            @RequestBody UserInputDTO user) {
        user.setUuid(uuid);
        UserOutputDTO userUpdated = userService.updateProfile(user);
        return ResponseEntity.ok(userUpdated);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('CLIENT')")
    @PutMapping("/{uuid}/password")
    public ResponseEntity<?> updatePassword(
            @PathVariable UUID uuid,
            @Valid @RequestBody PasswordInputDTO passwordDTO) {
        UserOutputDTO userUpdated = userService.updatePassword(uuid, passwordDTO);
        return ResponseEntity.ok(userUpdated);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('CLIENT')")
    @PutMapping("/{uuid}/picture")
    public ResponseEntity<?> updateProfilePicture(
            @PathVariable UUID uuid,
            @RequestBody UserInputDTO user) {
        user.setUuid(uuid);
        UserOutputDTO userUpdated = userService.updateProfilePicture(user);
        return ResponseEntity.ok(userUpdated);
    }
}
