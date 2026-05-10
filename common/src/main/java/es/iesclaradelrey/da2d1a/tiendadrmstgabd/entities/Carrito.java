package es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"usuario_id", "reloj_id"}
                )
        }
)
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reloj_id")
    private Reloj reloj;

    @Min(1)
    private int unidades;

    private LocalDate fecha;

}
