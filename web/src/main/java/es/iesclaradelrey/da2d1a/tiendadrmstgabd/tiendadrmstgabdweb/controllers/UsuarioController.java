package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.dto.UsuarioRegistroDto;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories.RolRepository;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder, RolRepository rolRepository) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
    }

    // Muestra el formulario de registro
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuarioRegistroDto", new UsuarioRegistroDto());
        return "usuarios/register";
    }

    // Procesa el formulario de registro
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("usuarioRegistroDto") UsuarioRegistroDto registroDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "usuarios/register";
        }

        // Si no acepta condiciones, volvemos al formulario con error
        if (!registroDto.isAceptaCondiciones()) {
            model.addAttribute("usuarioRegistroDto", registroDto);
            model.addAttribute("error", "Debes aceptar las condiciones para registrarte.");
            return "usuarios/register";
        }

        try {
            // Construimos la entidad Usuario desde el DTO
            Usuario usuario = new Usuario();
            usuario.setUsername(registroDto.getUsername());
            usuario.setNombre(registroDto.getNombre());
            usuario.setApellidos(registroDto.getApellidos());
            usuario.setEmail(registroDto.getEmail());
            usuario.setTelefono(registroDto.getTelefono());
            usuario.setFechaNacimiento(registroDto.getFechaNacimiento());
            // Codificamos la contraseña con bcrypt antes de guardarla
            usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));
            // La fecha de registro la asigna el servidor automáticamente
            usuario.setFechaRegistro(LocalDateTime.now());
            rolRepository.findById("USER").ifPresent(rol -> {
                usuario.getRoles().add(rol);
            });

            usuarioService.guardar(usuario);

        } catch (Exception e) {
            // Si hay error (email o username duplicado, etc.) volvemos al formulario
            model.addAttribute("usuarioRegistroDto", registroDto);
            model.addAttribute("error", "Error al registrar: " + e.getMessage());
            return "usuarios/register";
        }

        return "redirect:/login?success";
    }

    // Muestra el formulario de login
    @GetMapping("/login")
    public String login() {
        return "usuarios/login";
    }
}