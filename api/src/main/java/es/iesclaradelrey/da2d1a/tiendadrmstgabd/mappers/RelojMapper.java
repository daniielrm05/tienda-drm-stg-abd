package es.iesclaradelrey.da2d1a.tiendadrmstgabd.mappers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos.RelojDto;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Reloj;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoriaRelojMapper.class})
public interface RelojMapper {
    // Mapeamos el nombre de la marca desde la relación
    @Mapping(source = "marca.nombre", target = "nombreMarca")
    RelojDto toDto(Reloj reloj);

    List<RelojDto> toDtos(List<Reloj> relojes);
}