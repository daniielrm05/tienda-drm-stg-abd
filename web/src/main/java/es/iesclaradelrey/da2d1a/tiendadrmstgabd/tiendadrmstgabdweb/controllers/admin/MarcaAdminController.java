package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.MarcaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MarcaAdminController {

    private final MarcaService marcaService;

    public MarcaAdminController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    //Redireccion por si el usuario escribe la url con una barra al final
    @GetMapping("/admin/marcas/")
    public String redirigir() {
        return "redirect:/admin/marcas";
    }

    //Listado de marcas
    @GetMapping("/admin/marcas")
    public String listar(Model model) {
        model.addAttribute("marcas", marcaService.buscarTodo());
        return "admin/marcas/listado";
    }

    //Formulario para crear
    @GetMapping("/admin/marcas/new")
    public String crearForm(Model model) {
        model.addAttribute("marca", new Marca());
        return "admin/marcas/formulario";
    }

    //Guardar / Actualizar — si falla, volvemos al formulario con el error y los datos que había
    @PostMapping("/admin/marcas")
    public String guardar(@ModelAttribute Marca marca, Model model) {
        try {
            marcaService.guardar(marca);
            return "redirect:/admin/marcas";
        } catch (Exception e) {
            model.addAttribute("marca", marca);
            model.addAttribute("error", e.getMessage());
            return "admin/marcas/formulario";
        }
    }

    //Formulario editar
    @GetMapping("/admin/marcas/{id}/edit")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("marca", marcaService.buscarPorId(id).orElseThrow());
        return "admin/marcas/formulario";
    }

    //Formulario para borrar
    @GetMapping("/admin/marcas/{id}/delete")
    public String eliminarForm(@PathVariable Long id, Model model) {
        model.addAttribute("marca", marcaService.buscarPorId(id).orElseThrow());
        return "admin/marcas/eliminar";
    }

    //Borrar — si falla, volvemos a la pantalla de confirmación con el error
    @PostMapping("/admin/marcas/{id}/delete")
    public String borrar(@PathVariable Long id, Model model) {
        try {
            marcaService.eliminar(id);
            return "redirect:/admin/marcas";
        } catch (Exception e) {
            model.addAttribute("marca", marcaService.buscarPorId(id).orElseThrow());
            model.addAttribute("error", e.getMessage());
            return "admin/marcas/eliminar";
        }
    }
}