package es.iesclaradelrey.da2d1a.tiendadrmstgadb.repositorie;

import es.iesclaradelrey.da2d1a.tiendadrmstgadb.entitie.CategoriaReloj;

import java.util.Collection;
import java.util.Optional;

/**
 * Interfaz específica para el repositorio de categorías.
 */

public interface CategoriaRelojRepository {

    Collection<CategoriaReloj> buscarTodo();
    Optional<CategoriaReloj> buscarPorId(Long id);
    void guardar(CategoriaReloj categoria);
    void eliminar(Long id);

}
