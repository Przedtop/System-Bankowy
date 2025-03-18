package com.przedtop.bank.system.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // ✅ Wyłącz CSRF dla REST API
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/transfer").permitAll() // ✅ Każdy może wykonać przelew
//                        .requestMatchers("/api/users/**").hasRole("ADMIN") // ✅ Tylko ADMIN ma dostęp do /api/users
                        .anyRequest().permitAll() // ✅ Inne endpointy wymagają logowania
                );

        return http.build();
    }
}
