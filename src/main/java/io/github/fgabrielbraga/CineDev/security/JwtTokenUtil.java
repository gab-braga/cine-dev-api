package io.github.fgabrielbraga.CineDev.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt-expiration-time}")
    private long jwtExpirationTime;

    @Value("${jwt-secret-key}")
    private String jwtSecretKey;

    public boolean validateJwtToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(jwtSecretKey))
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            return false;
        }
    }

    public String extractEmailFromJwt(String token) {
        return JWT.require(Algorithm.HMAC512(jwtSecretKey))
                .build()
                .verify(token)
                .getSubject();
    }

    public String generateJwtToken(String uuid, String email, String role) {
        Date expirationDate = new Date(System.currentTimeMillis() + jwtExpirationTime);
        return JWT.create()
                .withSubject(uuid)
                .withClaim("name", email)
                .withClaim("role", role)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC512(jwtSecretKey));
    }
}
