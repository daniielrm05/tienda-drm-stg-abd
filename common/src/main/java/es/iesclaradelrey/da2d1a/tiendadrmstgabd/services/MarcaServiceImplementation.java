package es.iesclaradelrey.da2d1a.tiendadrmstgabd.services;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories.MarcaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Implementación de la interfaz del servicio de marca.
 */

@Service
public class MarcaServiceImplementation implements MarcaService {

    private final MarcaRepository repository;

    public MarcaServiceImplementation(MarcaRepository repository) {
        this.repository = repository;
    }

    // Devuelve todas las marcas
    @Override
    public Collection<Marca> buscarTodo() {
        return repository.findAll();
    }

    // Devuelve una marca por su id
    @Override
    public Optional<Marca> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Devuelve una marca por su nombre
    @Override
    public Optional<Marca> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    // Guarda una marca
    @Override
    public void guardar(Marca marca) {
        // Se comprueba si existe alguna marca con ese nombre en la BD
        Optional<Marca> marcaExistente = repository.findByNombre(marca.getNombre());

        // Si el nombre ya existe, se valida si es un duplicado real o se esta editando
        if (marcaExistente.isPresent()) {

            // Caso A: Es una marca nueva (id es null) pero el nombre ya existe -> ERROR
            if (marca.getId() == null) {
                throw new RuntimeException("Ya existe una marca con ese nombre");
            }

            // Caso B: Estoy editando (id no es null), el nombre existe,
            // pero el ID de la BD es distinto al mío -> ERROR (otro ya usa ese nombre)
            if (!marca.getId().equals(marcaExistente.get().getId())) {
                throw new RuntimeException("Ya existe otra marca con ese nombre");
            }

            // Si el ID de marcaExistente es IGUAL al de marca.getId(),
            // se ignora el if y se deja que guarde (es un Update legal).
        }

        // Si pasa las validaciones, se guarda
        repository.save(marca);
    }

    // Elimina una marca por su id
    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}