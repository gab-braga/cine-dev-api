package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.input.UserInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.UserOutputDTO;
import io.github.fgabrielbraga.CineDev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{uuid}")
    public ResponseEntity<UserOutputDTO> findById(@PathVariable UUID uuid) {
        Optional<UserOutputDTO> userOptional = userService.findById(uuid);
        return userOptional
                .map(userFound -> ResponseEntity.ok(userFound))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserOutputDTO>> findAll() {
        List<UserOutputDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserOutputDTO> create(@RequestBody UserInputDTO user) {
        user.setUuid(null);
        UserOutputDTO userSaved = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UserOutputDTO> update(@PathVariable UUID uuid, @RequestBody UserInputDTO user) {
        Optional<UserOutputDTO> userOptional = userService.findById(uuid);
        return userOptional
                .map(userFound -> {
                    user.setUuid(uuid);
                    UserOutputDTO userUpdated = userService.save(user);
                    return ResponseEntity.ok(userUpdated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid) {
        Optional<UserOutputDTO> userOptional = userService.findById(uuid);
        return userOptional
                .map(userFound -> {
                    userService.deleteById(uuid);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
