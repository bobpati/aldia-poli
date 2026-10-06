package com.aldia.poli.repository;
import com.aldia.poli.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface MateriaRepository extends JpaRepository<Materia, Long> {
    List<Materia> findBySemestreEstudianteCorreo(String correo);
}
