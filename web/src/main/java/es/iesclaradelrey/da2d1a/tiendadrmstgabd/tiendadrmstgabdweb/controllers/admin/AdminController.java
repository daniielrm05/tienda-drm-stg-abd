package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    //Si el usuario pone en el navegador url/admin/ se le redirige a la pagina de inicio
    @GetMapping("/admin/")
    public String redirigirAdmin() {
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String inicioAdmin() {
        return "admin/index";                 //vista de la pagina de inicio de admin
    }

}
