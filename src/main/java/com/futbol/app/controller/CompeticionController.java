package com.futbol.app.controller;

import com.futbol.app.entity.Competicion;
import com.futbol.app.service.CompeticionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/competiciones")
@RequiredArgsConstructor
public class CompeticionController {

    private final CompeticionService competicionService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("competiciones", competicionService.findAll());
        return "competiciones/listar";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("competicion", new Competicion());
        return "competiciones/form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        competicionService.findById(id).ifPresent(competicion ->
            model.addAttribute("competicion", competicion)
        );
        return "competiciones/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Competicion competicion) {
        competicionService.save(competicion);
        return "redirect:/competiciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        competicionService.deleteById(id);
        return "redirect:/competiciones";
    }
}
