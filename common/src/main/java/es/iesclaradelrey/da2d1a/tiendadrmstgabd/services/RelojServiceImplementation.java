package es.iesclaradelrey.da2d1a.tiendadrmstgabd.services;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Reloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories.RelojRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Implementación de la interfaz del servicio de reloj.
 */

@Service
public class RelojServiceImplementation implements RelojService {

    private final RelojRepository repository;

    public RelojServiceImplementation(RelojRepository repository) { this.repository = repository; }

    // Devuelve todos los relojes
    @Override
    public Collection<Reloj> buscarTodo() { return repository.findAll(); }

    // Devuelve un reloj por su id
    @Override
    public Optional<Reloj> buscarPorId(Long id) { return repository.findById(id); }

    //Devuelve un reloj por su codigo
    @Override
    public Optional<Reloj> buscarPorCodigo(String codigo) { return repository.findByCodigo(codigo); }

    // Guarda reloj
    @Override
    public void guardar(Reloj reloj) {
        // Si existe un reloj con el mismo codigo lanza una excepción
        if (repository.existsByCodigo(reloj.getCodigo())) {
            throw new RuntimeException("Reloj ya existe");
        }
        repository.save(reloj);
    }

    // Elimina reloj
    @Override
    public void eliminar(Long id) { repository.deleteById(id); }

}
