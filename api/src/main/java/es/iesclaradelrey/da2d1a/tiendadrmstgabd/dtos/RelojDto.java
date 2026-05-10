package es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RelojDto {
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String imagen;
    private Double precio;
    private Integer descuento;
    private String nombreMarca;
    private List<CategoriaRelojDto> categorias;
}