package io.github.fgabrielbraga.CineDev.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth0.jwt.JWT;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt-key}")
    private String jwtKey;

    @Autowired
    private UserSecurityService userSecurityService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!validateJwtFromHeader(request)){
            filterChain.doFilter(request, response);
            return;
        }
        String token = extractJwtFromHeader(request);
        if(!validateJwtToken(token)){
            filterChain.doFilter(request, response);
            return;
        }
        String email = extractEmailFromJwt(token);
        UserDetails user = userSecurityService.loadUserByUsername(email);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        user.getAuthorities()
                )
        );
        filterChain.doFilter(request, response);
    }

    private String extractJwtFromHeader(HttpServletRequest request) {
        String header = request.getHeader(AUTH_HEADER_NAME);
        return header.substring(TOKEN_PREFIX.length());
    }

    private boolean validateJwtFromHeader(HttpServletRequest request) {
        String header = request.getHeader(AUTH_HEADER_NAME);
        return Optional.ofNullable(header).map(authHeader ->
                authHeader.startsWith(TOKEN_PREFIX)
        ).orElse(false);
    }

    private boolean validateJwtToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(this.jwtKey))
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            return false;
        }
    }

    private String extractEmailFromJwt(String token) {
        return JWT.require(Algorithm.HMAC512(this.jwtKey))
                .build()
                .verify(token)
                .getSubject();
    }
}
