package com.aldia.poli.controller;

import com.aldia.poli.service.NotificacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping("/notificaciones")
public class NotificacionController {
    private final NotificacionService service;
    public NotificacionController(NotificacionService service) { this.service = service; }
    @GetMapping public String listar(Principal p, Model m) { m.addAttribute("notificaciones", service.listar(p.getName())); return "notificaciones/lista"; }
    @PostMapping("/{id}/leer") public String leer(@PathVariable Long id, Principal p) { service.marcarLeida(id, p.getName()); return "redirect:/notificaciones"; }
}
