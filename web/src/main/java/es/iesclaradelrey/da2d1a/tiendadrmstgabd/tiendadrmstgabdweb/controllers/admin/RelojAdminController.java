package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Reloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.CategoriaRelojService;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.MarcaService;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.RelojService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RelojAdminController {

    private final CategoriaRelojService categoriaRelojService;
    private final MarcaService marcaService;
    private final RelojService relojService;

    public RelojAdminController(CategoriaRelojService categoriaRelojService, MarcaService marcaService, RelojService relojService) {
        this.categoriaRelojService = categoriaRelojService;
        this.marcaService = marcaService;
        this.relojService = relojService;
    }

    //Redireccion por si el usuario escribe la url con una barra al final
    @GetMapping("/admin/relojes/")
    public String redirigirReloj() {
        return "redirect:/admin/relojes";
    }

    //Listado de relojes
    @GetMapping("/admin/relojes")
    public String relojAdmin(Model model) {
        model.addAttribute("relojes", relojService.buscarTodo());
        return "admin/relojes/listado";
    }

    //Formulario para crear
    @GetMapping("/admin/relojes/new")
    public String relojCrear(Model model) {
        model.addAttribute("reloj", new Reloj());
        model.addAttribute("todasMarcas", marcaService.buscarTodo());
        model.addAttribute("todasCategorias", categoriaRelojService.buscarTodo());
        return "admin/relojes/formulario";
    }

    //Guardar / Actualizar — si falla, volvemos al formulario con el error y los datos que había
    //También recargamos marcas y categorías para que el formulario se muestre correctamente
    @PostMapping("/admin/relojes")
    public String relojGuardar(@ModelAttribute Reloj reloj, Model model) {
        try {
            relojService.guardar(reloj);
            return "redirect:/admin/relojes";
        } catch (Exception e) {
            model.addAttribute("reloj", reloj);
            model.addAttribute("todasMarcas", marcaService.buscarTodo());
            model.addAttribute("todasCategorias", categoriaRelojService.buscarTodo());
            model.addAttribute("error", e.getMessage());
            return "admin/relojes/formulario";
        }
    }

    //Formulario editar
    @GetMapping("/admin/relojes/{id}/edit")
    public String editarReloj(@PathVariable Long id, Model model) {
        model.addAttribute("reloj", relojService.buscarPorId(id).orElseThrow());
        model.addAttribute("todasMarcas", marcaService.buscarTodo());
        model.addAttribute("todasCategorias", categoriaRelojService.buscarTodo());
        return "admin/relojes/formulario";
    }

    //Formulario Borrar
    @GetMapping("/admin/relojes/{id}/delete")
    public String eliminarReloj(@PathVariable Long id, Model model) {
        model.addAttribute("reloj", relojService.buscarPorId(id).orElseThrow());
        return "admin/relojes/eliminar";
    }

    //Borrar — si falla, volvemos a la pantalla de confirmación con el error
    @PostMapping("/admin/relojes/{id}/delete")
    public String borrarReloj(@PathVariable Long id, Model model) {
        try {
            relojService.eliminar(id);
            return "redirect:/admin/relojes";
        } catch (Exception e) {
            model.addAttribute("reloj", relojService.buscarPorId(id).orElseThrow());
            model.addAttribute("error", e.getMessage());
            return "admin/relojes/eliminar";
        }
    }
}