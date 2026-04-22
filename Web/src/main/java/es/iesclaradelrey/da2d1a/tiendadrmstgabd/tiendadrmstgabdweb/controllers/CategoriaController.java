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
public class CategoriaController {
    @Autowired
    private CategoriaRelojService service;

    //listado de categorias
    @GetMapping
    public String listado(HttpServletRequest request, Model model) {
        model.addAttribute("categorias", service.buscarTodo()); //mete todas las categorias en el modelo
        model.addAttribute("requestURI", request.getRequestURI()); //para que el menú de navegación (Navbar) saber en qué página estás e iluminar el botón correspondiente
        return "categorias/listado";
    }

    //detalle de la categoria
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, HttpServletRequest request, Model model) {
        Optional<CategoriaReloj> cat = service.buscarPorId(id);  //Obtiene la categoria del id

        if (cat.isPresent()) { //comprueba que exista esa categoria
            model.addAttribute("categoria", cat.get()); //mete la categoria del id en el modelo
            model.addAttribute("requestURI", request.getRequestURI()); //para que el menú de navegación (Navbar) saber en qué página estás e iluminar el botón correspondiente
            return "categorias/detalle";
        }

        return "redirect:/categorias"; // Si el ID no existe, vuelve atrás
    }
}