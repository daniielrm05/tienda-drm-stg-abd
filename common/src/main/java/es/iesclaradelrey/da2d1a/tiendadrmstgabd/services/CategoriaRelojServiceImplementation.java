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

<<<<<<< HEAD
    // Se limita a pasar la llamada al repositorio
    @Override
    public Collection<CategoriaReloj> buscarTodo() {
        return repository.buscarTodo();
    }

    // Se limita a pasar la llamada al repositorio
    @Override
    public Optional<CategoriaReloj> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    @Override
    public void guardar(CategoriaReloj categoria) {
        repository.guardar(categoria);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
=======
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
        // Buscamos si ya existe una categoría con ese nombre
        Optional<CategoriaReloj> categoriaExistente = repository.findByNombre(categoria.getNombre());

        if (categoriaExistente.isPresent()) {
            // Si existe, validamos si es un duplicado real o es la misma que estamos editando
            // Si el ID es null (nueva) o el ID es diferente al encontrado (otro registro tiene ese nombre)
            if (categoria.getId() == null || !categoria.getId().equals(categoriaExistente.get().getId())) {
                throw new RuntimeException("Ya existe una categoría con ese nombre");
            }
        }

        // Si no hay conflicto, guardamos (Spring hará INSERT o UPDATE según corresponda)
        repository.save(categoria);
    }

    //Elimina una categoria_reloj
    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
>>>>>>> drm
    }

}
