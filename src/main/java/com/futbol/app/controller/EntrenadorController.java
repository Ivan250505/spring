package com.futbol.app.controller;

import com.futbol.app.entity.Entrenador;
import com.futbol.app.service.EntrenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entrenadores")
@RequiredArgsConstructor
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("entrenadores", entrenadorService.findAll());
        return "entrenadores/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "entrenadores/form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        entrenadorService.findById(id).ifPresent(entrenador ->
            model.addAttribute("entrenador", entrenador)
        );
        return "entrenadores/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Entrenador entrenador) {
        entrenadorService.save(entrenador);
        return "redirect:/entrenadores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        entrenadorService.deleteById(id);
        return "redirect:/entrenadores";
    }
}
