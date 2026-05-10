package es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarritoItemDto {
    private Long idReloj;
    private String nombreReloj;
    private int unidades;
    private Double precioUnitario; // Ya con el descuento aplicado
    private Double importeProducto; // unidades * precioUnitario
}
