package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.MarcaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService service;

    public MarcaController(MarcaService service) {
        this.service = service;
    }

    // Responde a /marcas/{id} — muestra la marca y todos sus relojes ordenados alfabéticamente
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id,
                          HttpServletRequest request,
                          Model model) {

        Optional<Marca> marca = service.buscarPorId(id);

        if (marca.isPresent()) {
            // Ordena los relojes de la marca alfabéticamente usando Comparator
            List<?> relojesOrdenados = marca.get().getRelojes().stream()
                    .sorted(Comparator.comparing(r -> r.getNombre()))
                    .collect(Collectors.toList());

            model.addAttribute("marca", marca.get());
            model.addAttribute("relojes", relojesOrdenados);
            model.addAttribute("requestURI", request.getRequestURI());
            return "marcas/detalle";
        }

        return "redirect:/";
    }
}