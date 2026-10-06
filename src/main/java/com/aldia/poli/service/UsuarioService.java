package com.aldia.poli.service;

import com.aldia.poli.dto.RegistroDto;
import com.aldia.poli.model.Estudiante;
import com.aldia.poli.repository.EstudianteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    private final EstudianteRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(EstudianteRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Estudiante registrar(RegistroDto dto) {
        if (repository.existsByCorreo(dto.getCorreo().toLowerCase())) {
            throw new IllegalArgumentException("Ya existe una cuenta con ese correo.");
        }
        Estudiante e = new Estudiante();
        e.setNombre(dto.getNombre());
        e.setCorreo(dto.getCorreo().toLowerCase());
        e.setClave(passwordEncoder.encode(dto.getClave()));
        e.setProgramaAcademico(dto.getProgramaAcademico());
        e.setSede(dto.getSede());
        return repository.save(e);
    }

    public Estudiante porCorreo(String correo) {
        return repository.findByCorreo(correo).orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));
    }
}
