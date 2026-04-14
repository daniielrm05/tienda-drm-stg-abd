package es.iesclaradelrey.da2d1a.tiendadrmstgadb.entities;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Reloj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @Column(columnDefinition = "Text")
    private String descripcion;

    @NotNull
    private float precio;

}
