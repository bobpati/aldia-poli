package com.aldia.poli.service;

import com.aldia.poli.dto.ActividadDto;
import com.aldia.poli.model.*;
import com.aldia.poli.observer.RendimientoObserver;
import com.aldia.poli.repository.ActividadRepository;
import com.aldia.poli.repository.EventoCalendarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class ActividadService {
    private final ActividadRepository repository;
    private final EventoCalendarioRepository eventoRepository;
    private final MateriaService materiaService;
    private final CalculoService calculoService;
    private final NotificacionService notificacionService;
    private final UsuarioService usuarioService;

    public ActividadService(ActividadRepository repository, EventoCalendarioRepository eventoRepository,
                            MateriaService materiaService, CalculoService calculoService,
                            NotificacionService notificacionService, UsuarioService usuarioService) {
        this.repository = repository; this.eventoRepository = eventoRepository; this.materiaService = materiaService;
        this.calculoService = calculoService; this.notificacionService = notificacionService; this.usuarioService = usuarioService;
    }

    public List<ActividadEvaluativa> listar(Long materiaId, String correo) {
        materiaService.obtenerPropia(materiaId, correo);
        return repository.findByMateriaIdOrderByFechaEntregaAsc(materiaId);
    }

    public ActividadEvaluativa obtenerPropia(Long id, String correo) {
        ActividadEvaluativa a = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));
        if (!a.getMateria().getSemestre().getEstudiante().getCorreo().equalsIgnoreCase(correo)) throw new IllegalArgumentException("Acceso denegado");
        return a;
    }

    @Transactional
    public ActividadEvaluativa crear(Long materiaId, ActividadDto dto, String correo) {
        Materia materia = materiaService.obtenerPropia(materiaId, correo);
        double suma = materia.getActividades().stream().mapToDouble(ActividadEvaluativa::getPorcentaje).sum();
        if (suma + dto.getPorcentaje() > 100.0001) throw new IllegalArgumentException("La suma de porcentajes no puede superar 100%.");
        ActividadEvaluativa actividad = switch (dto.getTipo()) {
            case PARCIAL -> new Parcial();
            case QUIZ -> new Quiz();
            case TALLER -> new Taller();
        };
        actividad.setNombre(dto.getNombre());
        actividad.setPorcentaje(dto.getPorcentaje());
        actividad.setFechaEntrega(dto.getFechaEntrega());
        actividad.setMateria(materia);
        ActividadEvaluativa guardada = repository.save(actividad);

        EventoCalendario e = new EventoCalendario();
        e.setFecha(dto.getFechaEntrega());
        e.setDescripcion(dto.getNombre());
        e.setTipo(dto.getTipo().name());
        e.setMateria(materia);
        eventoRepository.save(e);
        return guardada;
    }

    @Transactional
    public void registrarCalificacion(Long actividadId, double valor, String observacion, String correo) {
        ActividadEvaluativa a = obtenerPropia(actividadId, correo);
        Estudiante estudiante = usuarioService.porCorreo(correo);
        a.agregarObserver(new RendimientoObserver(estudiante, a, calculoService, notificacionService));
        a.registrarCalificacion(valor, observacion);
        repository.save(a);
    }

    @Transactional public void eliminar(Long id, String correo) { repository.delete(obtenerPropia(id, correo)); }

    public List<ActividadEvaluativa> proximas(String correo, int dias) {
        LocalDate hoy = LocalDate.now();
        return repository.findByMateriaSemestreEstudianteCorreoAndFechaEntregaBetweenOrderByFechaEntregaAsc(correo, hoy, hoy.plusDays(dias));
    }
}
