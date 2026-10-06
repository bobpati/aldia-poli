package com.aldia.poli.repository;
import com.aldia.poli.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findTop30ByPersonaCorreoOrderByFechaEnvioDesc(String correo);
    long countByPersonaCorreoAndLeidaFalse(String correo);
    boolean existsByPersonaCorreoAndActividadIdAndMensajeContaining(String correo, Long actividadId, String texto);
}
