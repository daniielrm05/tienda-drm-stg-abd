package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Reloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.RelojService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/relojes")
public class RelojController {

    private final RelojService service;

    public RelojController(RelojService service) {
        this.service = service;
    }

    // Responde a /relojes — muestra el listado de todos los relojes
    @GetMapping
    public String listado(HttpServletRequest request, Model model) {
        model.addAttribute("relojes", service.buscarTodo());
        model.addAttribute("requestURI", request.getRequestURI());
        return "relojes/listado";
    }

    // Responde a /relojes/{id}/{nombre}
    @GetMapping("/{id}/{nombre}")
    public String detalle(@PathVariable Long id,
                          @PathVariable String nombre,
                          HttpServletRequest request,
                          Model model) {
        Optional<Reloj> reloj = service.buscarPorId(id);

        if (reloj.isPresent()) {
            model.addAttribute("reloj", reloj.get());
            model.addAttribute("requestURI", request.getRequestURI());
            return "relojes/detalle";
        }

        return "redirect:/relojes";
    }
}