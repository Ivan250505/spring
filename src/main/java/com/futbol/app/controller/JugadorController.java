package com.futbol.app.controller;

import com.futbol.app.entity.Jugador;
import com.futbol.app.service.ClubService;
import com.futbol.app.service.JugadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jugadores")
@RequiredArgsConstructor
public class JugadorController {

    private final JugadorService jugadorService;
    private final ClubService clubService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("jugadores", jugadorService.findAll());
        return "jugadores/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("clubes", clubService.findAll());
        return "jugadores/form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        jugadorService.findById(id).ifPresent(jugador ->
            model.addAttribute("jugador", jugador)
        );
        model.addAttribute("clubes", clubService.findAll());
        return "jugadores/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Jugador jugador,
                         @RequestParam(required = false) Long clubId) {

        Jugador jugadorGuardado = jugadorService.save(jugador);

        if (clubId != null) {
            clubService.findById(clubId).ifPresent(club -> {
                club.getJugadores().add(jugadorGuardado);
                clubService.save(club);
            });
        }

        return "redirect:/jugadores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        jugadorService.deleteById(id);
        return "redirect:/jugadores";
    }
}
