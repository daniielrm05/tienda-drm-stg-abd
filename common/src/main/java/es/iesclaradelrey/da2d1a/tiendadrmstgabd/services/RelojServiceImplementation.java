package es.iesclaradelrey.da2d1a.tiendadrmstgabd.services;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Marca;
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
        // Se comprueba si existe algún reloj con ese código en la BD
        Optional<Reloj> relojExistente = repository.findByCodigo(reloj.getCodigo());

        // Si el codigo ya existe, se valida si es un duplicado real o se esta editando
        if (relojExistente.isPresent()) {
            // Se valida el conflicto de código único
            // Se comprueba el ID del reloj que llega con el ID del que ya está en la BD
            if (reloj.getId() == null || !reloj.getId().equals(relojExistente.get().getId())) {
                throw new RuntimeException("Ya existe un reloj con el código: " + reloj.getCodigo());
            }
        }

        // Si pasa las validaciones, se guarda
        repository.save(reloj);
    }

    // Elimina reloj
    @Override
    public void eliminar(Long id) { repository.deleteById(id); }

}
