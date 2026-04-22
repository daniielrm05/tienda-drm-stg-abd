package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entitie.CategoriaReloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.service.CategoriaRelojService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/categorias")
public class TestController {
    @Autowired
    private CategoriaRelojService service;

    @GetMapping
    public String testList(HttpServletRequest request, Model model) {
        model.addAttribute("categorias", service.buscarTodo());
        model.addAttribute("requestURI", request.getRequestURI());
        return "categorias/listado";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, HttpServletRequest request, Model model) {
        Optional<CategoriaReloj> cat = service.buscarPorId(id);

        if (cat.isPresent()) {
            model.addAttribute("categoria", cat.get());
            model.addAttribute("requestURI", request.getRequestURI());
            return "categorias/detalle";
        }

        return "redirect:/categorias"; // Si el ID no existe, vuelve atrás
    }
}