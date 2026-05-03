package es.iesclaradelrey.da2d1a.tiendadrmstgabd.services;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Reloj;

import java.util.Collection;
import java.util.Optional;

/**
 * Interfaz para el servicio de reloj.
 * Define la lógica de negocio asociada a los relojes.
 */

public interface RelojService {
    Collection<Reloj> buscarTodo();
    Optional<Reloj> buscarPorId(Long id);
    Optional<Reloj> buscarPorCodigo(String codigo);
    void guardar(Reloj reloj);
    void eliminar(Long id);
}
