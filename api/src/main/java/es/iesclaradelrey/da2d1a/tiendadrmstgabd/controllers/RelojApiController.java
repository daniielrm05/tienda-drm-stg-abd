package es.iesclaradelrey.da2d1a.tiendadrmstgabd.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos.RelojDto;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.exceptions.ResourceNotFoundException;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.mappers.RelojMapper;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories.CategoriaRelojRepository;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories.RelojRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RelojApiController {

    private final RelojRepository relojRepository;
    private final CategoriaRelojRepository categoriaRepository;
    private final RelojMapper relojMapper;

    public RelojApiController(RelojRepository relojRepository,
                              CategoriaRelojRepository categoriaRepository,
                              RelojMapper relojMapper) {
        this.relojRepository = relojRepository;
        this.categoriaRepository = categoriaRepository;
        this.relojMapper = relojMapper;
    }

    // Listado de todos los relojes ordenados alfabéticamente
    @GetMapping("/products")
    public List<RelojDto> listarRelojes() {
        return relojMapper.toDtos(
                relojRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"))
        );
    }

    // Listado de relojes de una categoría ordenados alfabéticamente
    // Usa consulta derivada con parámetro Sort (no OrderBy en el nombre)
    @GetMapping("/categories/{categoryId}/products")
    public List<RelojDto> listarRelojesPorCategoria(@PathVariable Long categoryId) {
        // Verificamos que la categoría existe
        if (!categoriaRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Categoría con ID " + categoryId + " no encontrada");
        }

        return relojMapper.toDtos(
                relojRepository.findByCategorias_Id(categoryId, Sort.by(Sort.Direction.ASC, "nombre"))
        );
    }
}