package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.service.CategoriaRelojService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorias")
public class TestController {
    @Autowired
    private CategoriaRelojService service;

    @GetMapping
    public String testList(Model model) {
        model.addAttribute("categorias", service.buscarTodo());
        return "categorias/listado";
    }
}