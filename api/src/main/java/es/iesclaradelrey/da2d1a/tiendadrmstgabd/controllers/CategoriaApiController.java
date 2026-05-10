package es.iesclaradelrey.da2d1a.tiendadrmstgabd.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos.CategoriaRelojDto;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.mappers.CategoriaRelojMapper;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories.CategoriaRelojRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoriaApiController {

    private final CategoriaRelojRepository categoriaRepository;
    private final CategoriaRelojMapper categoriaMapper;

    public CategoriaApiController(CategoriaRelojRepository categoriaRepository,
                                  CategoriaRelojMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    // Listado de todas las categorías ordenadas alfabéticamente
    @GetMapping("/categories")
    public List<CategoriaRelojDto> listarCategorias() {
        return categoriaMapper.toDtos(
                categoriaRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"))
        );
    }
}