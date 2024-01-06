package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.dto.UserDTO;
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
    public ResponseEntity<UserDTO> findById(@PathVariable UUID uuid) {
        Optional<UserDTO> userOptional = userService.findById(uuid);
        return userOptional
                .map(userFound -> ResponseEntity.ok(userFound))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO user) {
        user.setUuid(null);
        UserDTO userSaved = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UserDTO> update(@PathVariable UUID uuid, @RequestBody UserDTO user) {
        Optional<UserDTO> userOptional = userService.findById(uuid);
        return userOptional
                .map(userFound -> {
                    user.setUuid(uuid);
                    UserDTO userUpdated = userService.save(user);
                    return ResponseEntity.ok(userUpdated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<UserDTO> delete(@PathVariable UUID uuid) {
        Optional<UserDTO> userOptional = userService.findById(uuid);
        return userOptional
                .map(userFound -> {
                    UserDTO userDeleted = userService.delete(userFound);
                    return ResponseEntity.ok(userDeleted);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
