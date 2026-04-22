package es.iesclaradelrey.da2d1a.tiendadrmstgabd.service;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entitie.CategoriaReloj;

import java.util.Collection;
import java.util.Optional;

/**
 * Interfaz para el servicio de categorías.
 * Define la lógica de negocio asociada a las categorías.
 */
public interface CategoriaRelojService {
    Collection<CategoriaReloj> buscarTodo();
    Optional<CategoriaReloj> buscarPorId(Long id);
    void guardar(CategoriaReloj categoria);
    void eliminar(Long id);
}
