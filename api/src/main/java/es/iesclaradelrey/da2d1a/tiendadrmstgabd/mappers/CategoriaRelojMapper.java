package es.iesclaradelrey.da2d1a.tiendadrmstgabd.mappers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.dto.CategoriaRelojDto;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.CategoriaReloj;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaRelojMapper {
    CategoriaRelojDto toDto(CategoriaReloj categoria);
    List<CategoriaRelojDto> toDtos(List<CategoriaReloj> categorias);
}