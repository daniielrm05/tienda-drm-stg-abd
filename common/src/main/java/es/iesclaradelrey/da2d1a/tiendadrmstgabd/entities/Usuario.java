package es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // Campos opcionales según PDF
    @Column(nullable = true)
    private String telefono;

    @Column(nullable = true)
    private LocalDate fechaNacimiento; // LocalDate es mejor para fechas sin hora

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_roles",
            joinColumns =  @JoinColumn(name = "usuarios"),
            inverseJoinColumns =  @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();
}