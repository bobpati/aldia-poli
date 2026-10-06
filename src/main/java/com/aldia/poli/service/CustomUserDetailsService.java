package com.aldia.poli.service;

import com.aldia.poli.model.Estudiante;
import com.aldia.poli.repository.EstudianteRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final EstudianteRepository repository;
    public CustomUserDetailsService(EstudianteRepository repository) { this.repository = repository; }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Estudiante e = repository.findByCorreo(correo.toLowerCase())
            .orElseThrow(() -> new UsernameNotFoundException("Correo no registrado"));
        return User.withUsername(e.getCorreo())
            .password(e.getClave())
            .roles("ESTUDIANTE")
            .disabled(!e.isActivo())
            .build();
    }
}
