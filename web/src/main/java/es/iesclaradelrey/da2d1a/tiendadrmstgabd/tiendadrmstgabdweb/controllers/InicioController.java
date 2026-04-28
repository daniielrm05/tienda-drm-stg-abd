package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Controlador que gestiona la página de inicio de la tienda
@Controller
public class InicioController {

    // Responde a la URL raíz con o sin barra final: localhost:8081/ y localhost:8081
    @GetMapping("/")
    public String inicio(HttpServletRequest request, Model model) {
        // Añade la URI al modelo para que Thymeleaf detecte la página activa en el navbar
        model.addAttribute("requestURI", request.getRequestURI());
        return "index";
    }

}