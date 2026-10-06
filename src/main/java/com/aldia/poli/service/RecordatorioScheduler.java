package com.aldia.poli.service;

import com.aldia.poli.model.ActividadEvaluativa;
import com.aldia.poli.repository.ActividadRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Component
public class RecordatorioScheduler {
    private final ActividadRepository actividadRepository;
    private final NotificacionService notificacionService;

    public RecordatorioScheduler(ActividadRepository actividadRepository, NotificacionService notificacionService) {
        this.actividadRepository = actividadRepository; this.notificacionService = notificacionService;
    }

    @Scheduled(cron = "0 0 * * * *")
    @Transactional
    public void generarRecordatorios() {
        LocalDate hoy = LocalDate.now();
        for (int dias : new int[]{1, 2}) {
            actividadRepository.findAll().stream()
                .filter(a -> a.getCalificacion() == null && a.getFechaEntrega().equals(hoy.plusDays(dias)))
                .forEach(a -> crearSiNoExiste(a, dias));
        }
    }

    private void crearSiNoExiste(ActividadEvaluativa a, int dias) {
        String correo = a.getMateria().getSemestre().getEstudiante().getCorreo();
        String texto = dias == 1 ? "vence en 24 horas" : "vence en 48 horas";
        if (!notificacionService.existeRecordatorio(correo, a.getId(), texto)) {
            notificacionService.enviar(a.getMateria().getSemestre().getEstudiante(),
                "Recordatorio: " + a.getNombre() + " de " + a.getMateria().getNombre() + " " + texto + ".", a);
        }
    }
}
