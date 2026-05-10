package es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CarritoResponseDto {
    private List<CarritoItemDto> items; // La lista de todos los relojes
    private Double importeTotal;       // La suma de todo
}