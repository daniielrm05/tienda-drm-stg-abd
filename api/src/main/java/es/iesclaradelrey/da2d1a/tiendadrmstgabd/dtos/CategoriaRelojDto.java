package es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaRelojDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private String imagen;
}