package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.input.UserInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.UserOutputDTO;
import io.github.fgabrielbraga.CineDev.model.User;
import io.github.fgabrielbraga.CineDev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<UserOutputDTO> findAll(String name, String email, String cpf) {
        List<User> users = userRepository.findAllWithFilter(name, email, cpf);
        return users.stream().map(UserOutputDTO::ofUser).toList();
    }

    public UserOutputDTO save(UserInputDTO userDTO) {
        User user = UserInputDTO.parseUser(userDTO);
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        User userSaved = userRepository.save(user);
        return UserOutputDTO.ofUser(userSaved);
    }

    public UserOutputDTO update(UserInputDTO userDTO) {
        Optional<User> userOpt = userRepository.findById(userDTO.getUuid());
        return userOpt.map(userFound -> {
            userFound.setName(userDTO.getName());
            userFound.setCpf(userDTO.getCpf());
            userFound.setRole(userDTO.getRole());
            userFound.setEmail(userDTO.getEmail());
            userFound.setPhoneNumber(userDTO.getPhoneNumber());
            User userSaved = userRepository.save(userFound);
            return UserOutputDTO.ofUser(userSaved);
        }).orElseThrow();
    }

    public UserOutputDTO updateProfilePicture(UserInputDTO userDTO) {
        Optional<User> userOpt = userRepository.findById(userDTO.getUuid());
        return userOpt.map(userFound -> {
            userFound.setProfilePicture(userDTO.getProfilePicture());
            User userSaved = userRepository.save(userFound);
            return UserOutputDTO.ofUser(userSaved);
        }).orElseThrow();
    }

    public void deleteById(UUID uuid) {
        userRepository.deleteById(uuid);
    }

    @Transactional
    public void disable(UUID uuid) {
        userRepository.disableUserById(uuid);
    }

    @Transactional
    public void enable(UUID uuid) {
        userRepository.enableUserById(uuid);
    }
}
