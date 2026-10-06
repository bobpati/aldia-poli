package com.aldia.poli.service;

import com.aldia.poli.model.*;
import com.aldia.poli.repository.NotificacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class NotificacionService {
    private final NotificacionRepository repository;
    public NotificacionService(NotificacionRepository repository) { this.repository = repository; }

    @Transactional
    public Notificacion enviar(Persona persona, String mensaje, ActividadEvaluativa actividad) {
        Notificacion n = new Notificacion();
        n.setPersona(persona); n.setMensaje(mensaje); n.setActividad(actividad);
        return repository.save(n);
    }
    public List<Notificacion> listar(String correo) { return repository.findTop30ByPersonaCorreoOrderByFechaEnvioDesc(correo); }
    public long noLeidas(String correo) { return repository.countByPersonaCorreoAndLeidaFalse(correo); }
    @Transactional public void marcarLeida(Long id, String correo) {
        Notificacion n = repository.findById(id).orElseThrow();
        if (!n.getPersona().getCorreo().equalsIgnoreCase(correo)) throw new IllegalArgumentException("Acceso denegado");
        n.setLeida(true); repository.save(n);
    }
    public boolean existeRecordatorio(String correo, Long actividadId, String texto) {
        return repository.existsByPersonaCorreoAndActividadIdAndMensajeContaining(correo, actividadId, texto);
    }
}
