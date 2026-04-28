package es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Reloj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interfaz específica para el repositorio de reloj.
 */

public interface RelojRepository extends JpaRepository <Reloj, Long> {

    Optional<Reloj> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);

}
