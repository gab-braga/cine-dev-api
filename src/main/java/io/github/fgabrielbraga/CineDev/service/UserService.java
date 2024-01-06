package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.UserDTO;
import io.github.fgabrielbraga.CineDev.model.User;
import io.github.fgabrielbraga.CineDev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserDTO> findById(UUID uuid) {
        Optional<User> userOpt = userRepository.findById(uuid);
        return userOpt.map(UserDTO::new);
    }

    public Optional<UserDTO> findByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.map(UserDTO::new);
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public UserDTO save(UserDTO userDTO) {
        User user = UserDTO.convert(userDTO);
        User userSaved = userRepository.save(user);
        return new UserDTO(userSaved);
    }

    public UserDTO delete(UserDTO userDTO) {
        User user = UserDTO.convert(userDTO);
        userRepository.delete(user);
        return userDTO;
    }
}
