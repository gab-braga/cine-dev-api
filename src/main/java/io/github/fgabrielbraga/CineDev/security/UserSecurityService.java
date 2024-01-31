package io.github.fgabrielbraga.CineDev.security;

import io.github.fgabrielbraga.CineDev.model.User;
import io.github.fgabrielbraga.CineDev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Optional<User> userOpt = userRepository.findByEmail(username);
        User user = userOpt.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserSecurity(user);
    }
}
