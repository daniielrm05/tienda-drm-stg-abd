package es.iesclaradelrey.da2d1a.tiendadrmstgabd.mappers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos.CarritoItemDto;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos.CarritoResponseDto;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Carrito;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CarritoMapper {

    @Mapping(target = "idReloj", source = "reloj.id")
    @Mapping(target = "nombreReloj", source = "reloj.nombre")
    // Cálculo: precio unitario = precio - descuento
    @Mapping(target = "precioUnitario", expression = "java(item.getReloj().getPrecio() - item.getReloj().getDescuento())")
    // Cálculo: importe = unidades * (precio - descuento)
    @Mapping(target = "importeProducto", expression = "java(item.getUnidades() * (item.getReloj().getPrecio() - item.getReloj().getDescuento()))")
    CarritoItemDto toDto(Carrito item);

    List<CarritoItemDto> toDtoList(List<Carrito> items);

    // Este metodo une la lista de items y el total
    default CarritoResponseDto toResponseDto(List<Carrito> items, Double total) {
        CarritoResponseDto response = new CarritoResponseDto();
        response.setItems(toDtoList(items));
        response.setImporteTotal(total);
        return response;
    }
}
