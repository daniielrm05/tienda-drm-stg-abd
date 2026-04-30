package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.CategoriaReloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.CategoriaRelojService;
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

// Controlador que gestiona las peticiones relacionadas con las categorías de relojes
@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    // Inyección por constructor: recomendada frente a @Autowired en campo
    private final CategoriaRelojService service;

    public CategoriaController(CategoriaRelojService service) {
        this.service = service;
    }

    // Responde a /categorias — muestra el listado de todas las categorías
    @GetMapping
    public String listado(HttpServletRequest request, Model model) {
        // Obtiene todas las categorías del servicio y las añade al modelo
        model.addAttribute("categorias", service.buscarTodo());
        // Necesario para que el navbar sepa qué enlace marcar como activo
        model.addAttribute("requestURI", request.getRequestURI());
        return "categorias/listado";
    }

    // Responde a /categorias/{id} — muestra el detalle de una categoría concreta
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, HttpServletRequest request, Model model) {
        Optional<CategoriaReloj> cat = service.buscarPorId(id);

        if (cat.isPresent()) {
            // Ordena los relojes de la categoría alfabéticamente usando Comparator
            List<?> relojesOrdenados = cat.get().getRelojes().stream()
                    .sorted(Comparator.comparing(r -> r.getNombre()))
                    .collect(Collectors.toList());

            model.addAttribute("categoria", cat.get());
            model.addAttribute("relojes", relojesOrdenados);
            model.addAttribute("requestURI", request.getRequestURI());
            return "categorias/detalle";
        }

        return "redirect:/categorias";
    }
}