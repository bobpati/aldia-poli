package com.aldia.poli.controller;

import com.aldia.poli.model.Materia;
import com.aldia.poli.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping("/materias")
public class MateriaController {
    private final MateriaService service;
    private final SemestreService semestreService;
    private final CalculoService calculoService;
    public MateriaController(MateriaService service, SemestreService semestreService, CalculoService calculoService) {
        this.service = service; this.semestreService = semestreService; this.calculoService = calculoService;
    }
    @GetMapping public String listar(Principal p, Model m) { m.addAttribute("materias", service.listar(p.getName())); m.addAttribute("calculo", calculoService); return "materias/lista"; }
    @GetMapping("/nueva") public String nueva(Principal p, Model m) { m.addAttribute("materia", new Materia()); m.addAttribute("semestres", semestreService.listar(p.getName())); return "materias/form"; }
    @PostMapping public String guardar(@Valid @ModelAttribute Materia materia, BindingResult br, @RequestParam Long semestreId, Principal p, Model m) {
        if (br.hasErrors()) { m.addAttribute("semestres", semestreService.listar(p.getName())); return "materias/form"; }
        service.guardar(materia, semestreId, p.getName()); return "redirect:/materias";
    }
    @GetMapping("/{id}") public String detalle(@PathVariable Long id, Principal p, Model m) {
        Materia materia = service.obtenerPropia(id, p.getName());
        m.addAttribute("materia", materia); m.addAttribute("promedio", calculoService.promedioActual(materia));
        m.addAttribute("acumulado", calculoService.acumulado(materia)); m.addAttribute("evaluado", calculoService.porcentajeEvaluado(materia));
        return "materias/detalle";
    }
    @GetMapping("/{id}/meta")
    public String meta(@PathVariable Long id, @RequestParam(defaultValue="3.0") double meta, Principal p, Model m) {
        Materia materia = service.obtenerPropia(id, p.getName());
        m.addAttribute("materia", materia);
        m.addAttribute("meta", meta);
        m.addAttribute("necesaria", calculoService.notaNecesaria(materia, meta));
        return "actividades/meta";
    }

    @PostMapping("/{id}/eliminar") public String eliminar(@PathVariable Long id, Principal p) { service.eliminar(id, p.getName()); return "redirect:/materias"; }
}
