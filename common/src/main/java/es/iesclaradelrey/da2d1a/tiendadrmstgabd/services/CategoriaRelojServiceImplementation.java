package es.iesclaradelrey.da2d1a.tiendadrmstgabd.services;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.CategoriaReloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories.CategoriaRelojRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Implementación de la interfaz del servicio de categorías.
 */

@Service
public class CategoriaRelojServiceImplementation implements CategoriaRelojService {

    private final CategoriaRelojRepository repository;

    public CategoriaRelojServiceImplementation(CategoriaRelojRepository repository) {
        this.repository = repository;
    }

    // Devuelve todas las categoria_reloj
    @Override
    public Collection<CategoriaReloj> buscarTodo() {
        return repository.findAll();
    }

    // Busca una categoria_reloj por su id
    @Override
    public Optional<CategoriaReloj> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Devuelve una categoria_reloj por su nombre
    @Override
    public Optional<CategoriaReloj> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    // Guarda una categoria_reloj
    @Override
    public void guardar(CategoriaReloj categoria) {
        // Se asegura de no guardar / modificar una categoria_reloj con el mismo nombre
        if (repository.existsByNombre(categoria.getNombre())) {
            throw new RuntimeException("Ya existe una categoría con ese nombre");
        }
        repository.save(categoria);
    }

    //Elimina una categoria_reloj
    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}
