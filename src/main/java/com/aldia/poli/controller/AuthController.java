package com.aldia.poli.controller;

import com.aldia.poli.dto.RegistroDto;
import com.aldia.poli.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final UsuarioService usuarioService;
    public AuthController(UsuarioService usuarioService) { this.usuarioService = usuarioService; }

    @GetMapping("/") public String inicio() { return "redirect:/dashboard"; }
    @GetMapping("/login") public String login() { return "login"; }

    @GetMapping("/registro")
    public String registro(Model model) { model.addAttribute("registro", new RegistroDto()); return "registro"; }

    @PostMapping("/registro")
    public String registrar(@Valid @ModelAttribute("registro") RegistroDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) return "registro";
        try { usuarioService.registrar(dto); }
        catch (IllegalArgumentException e) { model.addAttribute("error", e.getMessage()); return "registro"; }
        return "redirect:/login?registrado";
    }
}
