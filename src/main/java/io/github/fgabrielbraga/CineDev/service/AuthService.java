package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.input.CredentialsInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.TokenOutputDTO;
import io.github.fgabrielbraga.CineDev.security.JwtTokenUtil;
import io.github.fgabrielbraga.CineDev.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManage;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    public TokenOutputDTO login(CredentialsInputDTO credentials) {
        Authentication authentication = authenticate(credentials);
        String token = generateToken(authentication);
        return new TokenOutputDTO(token);
    }

    private Authentication authenticate(CredentialsInputDTO credentials) {
        return this.authenticationManage.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(),
                        credentials.getPassword()
                )
        );
    }

    private String generateToken(Authentication authentication) {
        UserSecurity user = (UserSecurity) authentication.getPrincipal();
        String uuid = user.getUUID();
        String email = user.getUsername();
        String role = getRole(user.getAuthorities());
        return jwtTokenUtil.generateJwtToken(uuid, email, role);
    }

    private String getRole(Collection<? extends GrantedAuthority> authorities) {
        return Optional.ofNullable(authorities).map(authList -> {
            return authList.stream().findFirst().get().getAuthority();
        }).orElse(null);
    }
}
