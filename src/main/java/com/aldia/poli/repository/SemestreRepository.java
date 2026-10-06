package com.aldia.poli.repository;
import com.aldia.poli.model.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SemestreRepository extends JpaRepository<Semestre, Long> {
    List<Semestre> findByEstudianteCorreoOrderByFechaInicioDesc(String correo);
}
