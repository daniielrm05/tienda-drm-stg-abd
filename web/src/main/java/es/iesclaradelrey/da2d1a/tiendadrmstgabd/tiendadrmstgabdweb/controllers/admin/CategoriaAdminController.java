package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.CategoriaReloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.CategoriaRelojService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoriaAdminController {

    private final CategoriaRelojService categoriaService;

    public CategoriaAdminController(CategoriaRelojService service) {
        this.categoriaService = service;
    }

    //Redireccion por si el usuario escribe la url con una barra al final
    @GetMapping("/admin/categorias/")
    public String redirigir() {
        return "redirect:/admin/categorias";
    }

    //Listado de categorias
    @GetMapping("/admin/categorias")
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.buscarTodo());
        return "admin/categorias/listado";
    }

    //Formulario para crear
    @GetMapping("/admin/categorias/new")
    public String crearForm(Model model) {
        model.addAttribute("categoria", new CategoriaReloj());
        return "admin/categorias/formulario";
    }

    //Guardar / Actualizar — si falla, volvemos al formulario con el error y los datos que había
    @PostMapping("/admin/categorias")
    public String guardar(@ModelAttribute CategoriaReloj categoria, Model model) {
        try {
            categoriaService.guardar(categoria);
            return "redirect:/admin/categorias";
        } catch (Exception e) {
            model.addAttribute("categoria", categoria);
            model.addAttribute("error", e.getMessage());
            return "admin/categorias/formulario";
        }
    }

    //Formulario para actualizar
    @GetMapping("/admin/categorias/{id}/edit")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.buscarPorId(id).orElseThrow());
        return "admin/categorias/formulario";
    }

    //Formulario para borrar
    @GetMapping("/admin/categorias/{id}/delete")
    public String eliminarForm(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.buscarPorId(id).orElseThrow());
        return "admin/categorias/eliminar";
    }

    //Borrar — si falla, volvemos a la pantalla de confirmación con el error
    @PostMapping("/admin/categorias/{id}/delete")
    public String borrar(@PathVariable Long id, Model model) {
        try {
            categoriaService.eliminar(id);
            return "redirect:/admin/categorias";
        } catch (Exception e) {
            model.addAttribute("categoria", categoriaService.buscarPorId(id).orElseThrow());
            model.addAttribute("error", e.getMessage());
            return "admin/categorias/eliminar";
        }
    }
}