package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class PerfilController {

    private final UsuarioService usuarioService;

    public PerfilController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Muestra el perfil del usuario autenticado actualmente
    @GetMapping("/profile")
    public String perfil(Model model) {
        // Obtenemos el nombre de usuario del contexto de seguridad
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Optional<Usuario> usuario = usuarioService.buscarPorUsername(username);

        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            return "usuarios/profile";
        }

        return "redirect:/";
    }

    // Muestra el perfil de un usuario por su id
    // Solo accesible por el propio usuario o por ADMIN (configurado en SecurityFilterChain)
    @GetMapping("/profile/{userId}")
    public String perfilPorId(@PathVariable Long userId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usernameActual = auth.getName();

        Optional<Usuario> usuario = usuarioService.buscarPorId(userId);

        if (usuario.isPresent()) {
            // Si no es admin y el perfil no es el suyo, redirige al inicio
            boolean esAdmin = auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
            boolean esSuPerfil = usuario.get().getUsername().equals(usernameActual);

            if (!esAdmin && !esSuPerfil) {
                return "redirect:/";
            }

            model.addAttribute("usuario", usuario.get());
            return "usuarios/profile";
        }

        return "redirect:/";
    }
}