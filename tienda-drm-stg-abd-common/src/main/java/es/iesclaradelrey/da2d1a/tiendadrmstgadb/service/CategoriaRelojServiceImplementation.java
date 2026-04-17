package es.iesclaradelrey.da2d1a.tiendadrmstgadb.service;

import es.iesclaradelrey.da2d1a.tiendadrmstgadb.entitie.CategoriaReloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgadb.repositorie.CategoriaRelojRepository;
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

}
