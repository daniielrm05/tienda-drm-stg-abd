package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.dto.UsuarioRegistroDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
    /*
    private final UsuarioService usuarioService;       COMENTADO HASTA QUE SE CREE SERVICIO

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }*/


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Aquí es donde "nace" el objeto que espera el HTML
        // El nombre "usuarioDto" debe coincidir exactamente con el th:object del HTML
        model.addAttribute("usuarioRegistroDto", new UsuarioRegistroDto());

        return "usuarios/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("usuarioRegistroDto") UsuarioRegistroDto registroDto, Model model) {

        // Validar el checkbox
        if (!registroDto.isAceptaCondiciones()) {
            model.addAttribute("error", "Debes aceptar las condiciones para registrarte.");
            return "usuarios/register";
        }

        try {
            //usuarioService.registrar(registroDto); COMENTADO HASTA QUE SE CREE SERVICIO
        } catch (Exception e) {
            // Si el email ya existe o hay un error, volvemos al formulario
            model.addAttribute("error", "Error al registrar: " + e.getMessage());
            return "usuarios/register";
        }

        return "redirect:/login?success";
    }
}
