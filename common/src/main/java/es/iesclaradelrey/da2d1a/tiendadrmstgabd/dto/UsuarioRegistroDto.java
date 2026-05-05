package es.iesclaradelrey.da2d1a.tiendadrmstgabd.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class UsuarioRegistroDto {
    private String username;
    private String password;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private LocalDate fechaNacimiento;
    private boolean aceptaCondiciones;
}