package es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.CategoriaReloj;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del repositorio usando un Map en memoria.
 */

@Repository
public class CategoriaRelojRepositoryImplementation implements CategoriaRelojRepository {

    private final Map<Long, CategoriaReloj> categorias = new HashMap<>();

    //Busca todas las categorias de reloj en el map categorias
    @Override
    public Collection<CategoriaReloj> buscarTodo() {
        return categorias.values();
    }

    //Busca la categoria de reloj que coincida con el id
    @Override
    public Optional<CategoriaReloj> buscarPorId(Long id) {
        return Optional.ofNullable(categorias.get(id));
    }

    //Guarda categoria de reloj
    @Override
    public void guardar(CategoriaReloj categoria) {
        if (categoria.getId() != null) {
            categorias.put(categoria.getId(), categoria);
        }
    }

    //Elimina categoria de reloj
    @Override
    public void eliminar(Long id) {
        categorias.remove(id);
    }

}
