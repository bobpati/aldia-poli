package com.aldia.poli.repository;
import com.aldia.poli.model.ActividadEvaluativa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
public interface ActividadRepository extends JpaRepository<ActividadEvaluativa, Long> {
    List<ActividadEvaluativa> findByMateriaIdOrderByFechaEntregaAsc(Long materiaId);
    List<ActividadEvaluativa> findByMateriaSemestreEstudianteCorreoAndFechaEntregaBetweenOrderByFechaEntregaAsc(String correo, LocalDate desde, LocalDate hasta);
}
