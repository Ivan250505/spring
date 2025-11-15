package com.futbol.app.controller;

import com.futbol.app.entity.Club;
import com.futbol.app.service.AsociacionService;
import com.futbol.app.service.ClubService;
import com.futbol.app.service.CompeticionService;
import com.futbol.app.service.EntrenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clubes")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;
    private final AsociacionService asociacionService;
    private final EntrenadorService entrenadorService;
    private final CompeticionService competicionService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clubes", clubService.findAll());
        return "clubes/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("asociaciones", asociacionService.findAll());
        model.addAttribute("entrenadores", entrenadorService.findAll());
        model.addAttribute("competiciones", competicionService.findAll());
        return "clubes/form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        clubService.findById(id).ifPresent(club ->
            model.addAttribute("club", club)
        );
        model.addAttribute("asociaciones", asociacionService.findAll());
        model.addAttribute("entrenadores", entrenadorService.findAll());
        model.addAttribute("competiciones", competicionService.findAll());
        return "clubes/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Club club,
                         @RequestParam(required = false) Long asociacionId,
                         @RequestParam(required = false) Long entrenadorId,
                         @RequestParam(required = false) Long[] competicionIds) {

        if (asociacionId != null) {
            asociacionService.findById(asociacionId).ifPresent(club::setAsociacion);
        }

        if (entrenadorId != null) {
            entrenadorService.findById(entrenadorId).ifPresent(club::setEntrenador);
        }

        // Manejar competiciones seleccionadas
        if (competicionIds != null && competicionIds.length > 0) {
            club.getCompeticiones().clear();
            for (Long competicionId : competicionIds) {
                competicionService.findById(competicionId).ifPresent(competicion ->
                    club.getCompeticiones().add(competicion)
                );
            }
        } else {
            club.getCompeticiones().clear();
        }

        clubService.save(club);
        return "redirect:/clubes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        clubService.deleteById(id);
        return "redirect:/clubes";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Model model) {
        clubService.findById(id).ifPresent(club ->
            model.addAttribute("club", club)
        );
        return "clubes/ver";
    }
}
