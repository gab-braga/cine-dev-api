package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.input.UserInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.UserOutputDTO;
import io.github.fgabrielbraga.CineDev.model.User;
import io.github.fgabrielbraga.CineDev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Optional<UserOutputDTO> findById(UUID uuid) {
        Optional<User> userOpt = userRepository.findById(uuid);
        return userOpt.map(UserOutputDTO::ofUser);
    }

    public Optional<UserOutputDTO> findByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.map(UserOutputDTO::ofUser);
    }

    public List<UserOutputDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserOutputDTO::ofUser).toList();
    }

    public UserOutputDTO save(UserInputDTO userDTO) {
        User user = UserInputDTO.parseUser(userDTO);
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        User userSaved = userRepository.save(user);
        return UserOutputDTO.ofUser(userSaved);
    }

    public void deleteById(UUID uuid) {
        userRepository.deleteById(uuid);
    }
}
