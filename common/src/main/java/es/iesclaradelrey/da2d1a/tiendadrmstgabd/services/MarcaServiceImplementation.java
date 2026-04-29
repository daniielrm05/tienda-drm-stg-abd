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

        // Si ya existe una marca con el mismo nombre lanza una excepción
        if (repository.existsByNombre(marca.getNombre())) {
            throw new RuntimeException("Ya existe una marca con ese nombre");
        }

        repository.save(marca);
    }

    // Elimina una marca por su id
    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}