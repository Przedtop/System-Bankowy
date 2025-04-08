package com.przedtop.front.bank.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final AuthFailResponse authFailResponse;

    public SecurityConfig(AuthFailResponse authFailResponse) {
        this.authFailResponse = authFailResponse;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/adminDashboard/**").permitAll()
                        //will be done in the future ist only for temporary testing
                        .anyRequest().denyAll()
                )
                .exceptionHandling(handler -> handler
                        .accessDeniedHandler(authFailResponse)
                        .authenticationEntryPoint(authFailResponse)
                )
                .build();
    }
}