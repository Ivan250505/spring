package com.futbol.app.controller;

import com.futbol.app.entity.Asociacion;
import com.futbol.app.service.AsociacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asociaciones")
@RequiredArgsConstructor
public class AsociacionController {

    private final AsociacionService asociacionService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("asociaciones", asociacionService.findAll());
        return "asociaciones/listar";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        return "asociaciones/form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        asociacionService.findById(id).ifPresent(asociacion ->
            model.addAttribute("asociacion", asociacion)
        );
        return "asociaciones/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Asociacion asociacion) {
        asociacionService.save(asociacion);
        return "redirect:/asociaciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        asociacionService.deleteById(id);
        return "redirect:/asociaciones";
    }
}
