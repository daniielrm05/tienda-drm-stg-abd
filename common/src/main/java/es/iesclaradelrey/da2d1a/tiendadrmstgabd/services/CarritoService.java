package es.iesclaradelrey.da2d1a.tiendadrmstgabd.services;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Carrito;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Reloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface CarritoService {
    List<Carrito> buscarPorUsuario(Usuario usuario);

    Optional<Carrito> buscarPorUsuarioYReloj(
            Usuario usuario,
            Reloj reloj
    );

    Carrito guardar(Carrito item);

    void eliminar(Carrito item);

    void vaciar(Usuario usuario);

    Long contarProductosDistintos(Usuario usuario);

    Long contarUnidades(Usuario usuario);

    Double calcularImporteTotal(Usuario usuario);
}
