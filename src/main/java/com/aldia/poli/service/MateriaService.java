package com.aldia.poli.service;

import com.aldia.poli.model.Materia;
import com.aldia.poli.model.Semestre;
import com.aldia.poli.repository.MateriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MateriaService {
    private final MateriaRepository repository;
    private final SemestreService semestreService;
    public MateriaService(MateriaRepository repository, SemestreService semestreService) {
        this.repository = repository; this.semestreService = semestreService;
    }
    public List<Materia> listar(String correo) { return repository.findBySemestreEstudianteCorreo(correo); }
    public Materia obtenerPropia(Long id, String correo) {
        Materia m = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Materia no encontrada"));
        if (!m.getSemestre().getEstudiante().getCorreo().equalsIgnoreCase(correo)) throw new IllegalArgumentException("Acceso denegado");
        return m;
    }
    @Transactional public Materia guardar(Materia materia, Long semestreId, String correo) {
        Semestre semestre = semestreService.obtenerPropio(semestreId, correo);
        materia.setSemestre(semestre);
        return repository.save(materia);
    }
    @Transactional public void eliminar(Long id, String correo) { repository.delete(obtenerPropia(id, correo)); }
}
