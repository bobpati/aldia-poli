package com.aldia.poli.repository;
import com.aldia.poli.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}
