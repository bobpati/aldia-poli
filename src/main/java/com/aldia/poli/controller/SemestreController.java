package com.aldia.poli.controller;

import com.aldia.poli.model.Semestre;
import com.aldia.poli.service.SemestreService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping("/semestres")
public class SemestreController {
    private final SemestreService service;
    public SemestreController(SemestreService service) { this.service = service; }

    @GetMapping public String listar(Principal p, Model m) { m.addAttribute("semestres", service.listar(p.getName())); return "semestres/lista"; }
    @GetMapping("/nuevo") public String nuevo(Model m) { m.addAttribute("semestre", new Semestre()); return "semestres/form"; }
    @PostMapping public String guardar(@Valid @ModelAttribute Semestre semestre, BindingResult br, Principal p, Model m) {
        if (br.hasErrors()) return "semestres/form";
        try { service.guardar(semestre, p.getName()); }
        catch (IllegalArgumentException e) { m.addAttribute("error", e.getMessage()); return "semestres/form"; }
        return "redirect:/semestres";
    }
    @PostMapping("/{id}/eliminar") public String eliminar(@PathVariable Long id, Principal p) { service.eliminar(id, p.getName()); return "redirect:/semestres"; }
}
