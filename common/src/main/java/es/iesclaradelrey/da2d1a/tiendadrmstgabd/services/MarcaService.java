package es.iesclaradelrey.da2d1a.tiendadrmstgabd.services;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Marca;

import java.util.Collection;
import java.util.Optional;

/**
 * Interfaz para el servicio de marca.
 * Define la lógica de negocio asociada a las marcas.
 */

public interface MarcaService {
    Collection<Marca> buscarTodo();
    Optional<Marca> buscarPorId(Long id);
    Optional<Marca> buscarPorNombre(String nombre);
    void guardar(Marca marca);
    void eliminar(Long id);
}
