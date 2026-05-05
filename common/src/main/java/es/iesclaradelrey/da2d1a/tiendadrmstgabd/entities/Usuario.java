package es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String nombre;
    private String apellidos;

    @Column(unique = true)
    private String email;

    private String password;

    private LocalDateTime fechaRegistro;
}