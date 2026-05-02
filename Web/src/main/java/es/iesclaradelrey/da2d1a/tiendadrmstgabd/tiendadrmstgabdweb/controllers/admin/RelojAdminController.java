package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Reloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.CategoriaRelojService;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.MarcaService;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.RelojService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    //Guardar / Actualizar
    @PostMapping("/admin/relojes")
    public String relojGuardar(@ModelAttribute Reloj reloj) {
        relojService.guardar(reloj);
        return "redirect:/admin/relojes";
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

    //Borrar
    @PostMapping("/admin/relojes/{id}/delete")
    public String borrarReloj(@PathVariable Long id) {
        relojService.eliminar(id);
        return "redirect:/admin/relojes";
    }

}
