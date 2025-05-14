package ru.otus.thesis.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {}
//extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
//                .anyRequest().authenticated()
//         остальная конфигурация
//    }
//}