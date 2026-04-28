package es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.CategoriaReloj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Interfaz específica para el repositorio de categorías.
 */

public interface CategoriaRelojRepository extends JpaRepository<CategoriaReloj, Long> {

    //Busca una categoria_reloj por su nombre
    Optional<CategoriaReloj> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

}
