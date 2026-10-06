package com.aldia.poli.service;

import com.aldia.poli.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculoServiceTest {
    private final CalculoService service = new CalculoService();

    @Test
    void calculaPromedioActualConPorcentajeEvaluado() {
        Materia m = new Materia(); m.setNombre("Diseño"); m.setCodigo("DS1"); m.setCreditos(3);
        Parcial p = new Parcial(); p.setNombre("P1"); p.setPorcentaje(30); p.setMateria(m); p.registrarCalificacion(4.0, "");
        Quiz q = new Quiz(); q.setNombre("Q1"); q.setPorcentaje(20); q.setMateria(m); q.registrarCalificacion(3.5, "");
        m.getActividades().add(p); m.getActividades().add(q);
        assertEquals(3.8, service.promedioActual(m), 0.0001);
        assertEquals(1.9, service.acumulado(m), 0.0001);
    }

    @Test
    void calculaNotaNecesaria() {
        Materia m = new Materia(); m.setNombre("Diseño"); m.setCodigo("DS1"); m.setCreditos(3);
        Parcial p = new Parcial(); p.setNombre("P1"); p.setPorcentaje(50); p.setMateria(m); p.registrarCalificacion(3.8, "");
        m.getActividades().add(p);
        assertEquals(2.2, service.notaNecesaria(m, 3.0), 0.0001);
    }
}
