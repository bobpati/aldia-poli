package com.aldia.poli.service;

import com.aldia.poli.model.ActividadEvaluativa;
import com.aldia.poli.model.Materia;
import com.aldia.poli.model.Semestre;
import org.springframework.stereotype.Service;

@Service
public class CalculoService {
    public double acumulado(Materia materia) {
        return materia.getActividades().stream().mapToDouble(ActividadEvaluativa::calcularNota).sum();
    }

    public double porcentajeEvaluado(Materia materia) {
        return materia.getActividades().stream()
            .filter(a -> a.getCalificacion() != null)
            .mapToDouble(ActividadEvaluativa::getPorcentaje).sum();
    }

    public double promedioActual(Materia materia) {
        double porcentaje = porcentajeEvaluado(materia);
        return porcentaje == 0 ? 0 : acumulado(materia) / (porcentaje / 100.0);
    }

    public double notaNecesaria(Materia materia, double meta) {
        double restante = 100.0 - porcentajeEvaluado(materia);
        if (restante <= 0) return promedioActual(materia) >= meta ? 0.0 : 5.01;
        return (meta - acumulado(materia)) / (restante / 100.0);
    }

    public double promedioSemestral(Semestre semestre) {
        int creditos = semestre.getMaterias().stream().mapToInt(Materia::getCreditos).sum();
        if (creditos == 0) return 0;
        double suma = semestre.getMaterias().stream()
            .mapToDouble(m -> promedioActual(m) * m.getCreditos()).sum();
        return suma / creditos;
    }
}
