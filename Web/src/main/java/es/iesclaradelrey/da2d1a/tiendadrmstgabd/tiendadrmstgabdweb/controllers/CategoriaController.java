package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.CategoriaReloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.CategoriaRelojService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

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
        // Busca la categoría por su id
        Optional<CategoriaReloj> cat = service.buscarPorId(id);

        if (cat.isPresent()) {
            // Si existe, la añade al modelo y muestra la vista de detalle
            model.addAttribute("categoria", cat.get());
            model.addAttribute("requestURI", request.getRequestURI());
            return "categorias/detalle";
        }

        // Si el id no existe, redirige al listado
        return "redirect:/categorias";
    }
}