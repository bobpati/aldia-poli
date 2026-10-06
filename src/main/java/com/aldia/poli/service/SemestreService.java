package com.aldia.poli.service;

import com.aldia.poli.model.Semestre;
import com.aldia.poli.repository.SemestreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SemestreService {
    private final SemestreRepository repository;
    private final UsuarioService usuarioService;
    private final CalculoService calculoService;

    public SemestreService(SemestreRepository repository, UsuarioService usuarioService, CalculoService calculoService) {
        this.repository = repository; this.usuarioService = usuarioService; this.calculoService = calculoService;
    }
    public List<Semestre> listar(String correo) { return repository.findByEstudianteCorreoOrderByFechaInicioDesc(correo); }
    public Semestre obtenerPropio(Long id, String correo) {
        Semestre s = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Semestre no encontrado"));
        if (!s.getEstudiante().getCorreo().equalsIgnoreCase(correo)) throw new IllegalArgumentException("Acceso denegado");
        return s;
    }
    @Transactional public Semestre guardar(Semestre s, String correo) {
        if (s.getFechaFin() != null && s.getFechaInicio() != null && s.getFechaFin().isBefore(s.getFechaInicio()))
            throw new IllegalArgumentException("La fecha final no puede ser anterior a la inicial.");
        s.setEstudiante(usuarioService.porCorreo(correo));
        return repository.save(s);
    }
    @Transactional public void eliminar(Long id, String correo) { repository.delete(obtenerPropio(id, correo)); }
    public double promedio(Long id, String correo) { return calculoService.promedioSemestral(obtenerPropio(id, correo)); }
}
