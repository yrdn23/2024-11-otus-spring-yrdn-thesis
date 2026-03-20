package ru.otus.thesis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/actuator", "/actuator/health", "/actuator/info").permitAll()
                        .requestMatchers(
                                "/",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/swagger-resources",
                                "/webjars/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/favicon.ico"
                        ).permitAll()
                        .requestMatchers("/api/report/**").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers("/api/teacher/**").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers("/api/student/**").hasAnyRole("ADMIN", "STUDENT")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
