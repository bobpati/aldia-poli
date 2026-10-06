package com.aldia.poli.controller;

import com.aldia.poli.dto.ActividadDto;
import com.aldia.poli.model.TipoActividad;
import com.aldia.poli.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping("/actividades")
public class ActividadController {
    private final ActividadService service;
    private final MateriaService materiaService;
    private final CalculoService calculoService;
    public ActividadController(ActividadService service, MateriaService materiaService, CalculoService calculoService) {
        this.service = service; this.materiaService = materiaService; this.calculoService = calculoService;
    }

    @GetMapping("/nueva")
    public String nueva(@RequestParam Long materiaId, Principal p, Model m) {
        m.addAttribute("actividad", new ActividadDto()); m.addAttribute("materia", materiaService.obtenerPropia(materiaId, p.getName())); m.addAttribute("tipos", TipoActividad.values()); return "actividades/form";
    }

    @PostMapping
    public String crear(@RequestParam Long materiaId, @Valid @ModelAttribute("actividad") ActividadDto dto,
                        BindingResult br, Principal p, Model m) {
        if (br.hasErrors()) { m.addAttribute("materia", materiaService.obtenerPropia(materiaId, p.getName())); m.addAttribute("tipos", TipoActividad.values()); return "actividades/form"; }
        try { service.crear(materiaId, dto, p.getName()); }
        catch (IllegalArgumentException e) { m.addAttribute("error", e.getMessage()); m.addAttribute("materia", materiaService.obtenerPropia(materiaId, p.getName())); m.addAttribute("tipos", TipoActividad.values()); return "actividades/form"; }
        return "redirect:/materias/" + materiaId;
    }

    @PostMapping("/{id}/calificacion")
    public String calificar(@PathVariable Long id, @RequestParam double valor,
                            @RequestParam(required=false, defaultValue="") String observacion, Principal p) {
        var a = service.obtenerPropia(id, p.getName()); Long materiaId = a.getMateria().getId();
        service.registrarCalificacion(id, valor, observacion, p.getName());
        return "redirect:/materias/" + materiaId;
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, Principal p) {
        var a = service.obtenerPropia(id, p.getName()); Long materiaId = a.getMateria().getId(); service.eliminar(id, p.getName()); return "redirect:/materias/" + materiaId;
    }
}
