package es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Define las categorías principales de la tienda de relojes.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriaReloj {
    private Long id;
    private String nombre;
    private String descripcion;
    private String imagen;
}