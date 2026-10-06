package com.aldia.poli.controller;

import com.aldia.poli.model.Materia;
import com.aldia.poli.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class DashboardController {
    private final MateriaService materiaService;
    private final ActividadService actividadService;
    private final CalculoService calculoService;
    private final NotificacionService notificacionService;

    public DashboardController(MateriaService materiaService, ActividadService actividadService,
                               CalculoService calculoService, NotificacionService notificacionService) {
        this.materiaService = materiaService; this.actividadService = actividadService;
        this.calculoService = calculoService; this.notificacionService = notificacionService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        var materias = materiaService.listar(principal.getName());
        Map<Long, Double> promedios = new LinkedHashMap<>();
        for (Materia m : materias) promedios.put(m.getId(), calculoService.promedioActual(m));
        model.addAttribute("materias", materias);
        model.addAttribute("promedios", promedios);
        model.addAttribute("proximas", actividadService.proximas(principal.getName(), 7));
        model.addAttribute("noLeidas", notificacionService.noLeidas(principal.getName()));
        return "dashboard";
    }
}
