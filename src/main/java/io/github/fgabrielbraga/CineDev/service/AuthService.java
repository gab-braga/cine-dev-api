package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.input.CredentialsInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.TokenOutputDTO;
import io.github.fgabrielbraga.CineDev.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
        String role = getRole(authentication.getAuthorities());
        String token = jwtTokenUtil.generateJwtToken(credentials.getEmail(), role);
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

    private String getRole(Collection<? extends GrantedAuthority> authorities) {
        return Optional.ofNullable(authorities).map(authList -> {
            return authList.stream().findFirst().get().getAuthority();
        }).orElse("");
    }
}
