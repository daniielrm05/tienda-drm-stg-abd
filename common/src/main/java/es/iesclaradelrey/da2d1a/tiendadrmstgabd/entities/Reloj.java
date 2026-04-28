package es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reloj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "\\d{13}", message = "El EAN debe tener exactamente 13 dígitos")
    @Column(length = 13, unique = true)
    private String codigo;

    @NotBlank
    @Size(max = 200)
    private String nombre;

    @Size(max = 50)
    private String marca;

    @NotBlank
    @Size(max = 4000)
    private String descripcion;

    @Size(max = 500)
    private String imagen;

    @NotNull
    @Positive
    private Double precio;

    @NotNull
    @Min(0)
    @Max(99)
    private Integer descuento;

}
