package com.aldia.poli.config;

import com.aldia.poli.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, CustomUserDetailsService uds) throws Exception {
        http
            .userDetailsService(uds)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/registro", "/css/**", "/webjars/**").permitAll()
                .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("correo")
                .passwordParameter("clave")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login?error")
                .permitAll())
            .logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll());
        return http.build();
    }
}
