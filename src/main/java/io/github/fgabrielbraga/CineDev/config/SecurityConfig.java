package io.github.fgabrielbraga.CineDev.config;

import io.github.fgabrielbraga.CineDev.security.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final String ROLE_CLIENT = "CLIENT";
    private final String ROLE_ADMIN = "ADMIN";
    private final String[] PUBLIC_ROUTES = {"/auth/**"};
    private final String[] ADMIN_ROUTES = {"/auth/**"};
    private final String[] CLIENT_ROUTES = {"/auth/**"};

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(PUBLIC_ROUTES).permitAll()
                    .requestMatchers(CLIENT_ROUTES).hasRole(ROLE_CLIENT)
                    .requestMatchers(ADMIN_ROUTES).hasRole(ROLE_ADMIN)
                    .anyRequest().authenticated();
        });
        http.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
