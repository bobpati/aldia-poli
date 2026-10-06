package com.aldia.poli.observer;

import com.aldia.poli.model.ActividadEvaluativa;
import com.aldia.poli.model.Estudiante;
import com.aldia.poli.service.CalculoService;
import com.aldia.poli.service.NotificacionService;

public class RendimientoObserver implements IObserver {
    private final Estudiante estudiante;
    private final ActividadEvaluativa actividad;
    private final CalculoService calculoService;
    private final NotificacionService notificacionService;

    public RendimientoObserver(Estudiante estudiante, ActividadEvaluativa actividad,
                               CalculoService calculoService, NotificacionService notificacionService) {
        this.estudiante = estudiante;
        this.actividad = actividad;
        this.calculoService = calculoService;
        this.notificacionService = notificacionService;
    }

    @Override
    public void actualizar(String mensaje) {
        double promedio = calculoService.promedioActual(actividad.getMateria());
        String completo = mensaje + String.format(". Promedio actual de %s: %.2f", actividad.getMateria().getNombre(), promedio);
        notificacionService.enviar(estudiante, completo, actividad);
    }
}
