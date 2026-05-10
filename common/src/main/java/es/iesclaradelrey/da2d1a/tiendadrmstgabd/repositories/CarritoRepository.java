package es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Carrito;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Reloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    List<Carrito> findByUsuario(Usuario usuario);

    Optional<Carrito> findByUsuarioAndReloj(Usuario usuario, Reloj reloj);

    void deleteByUsuarioAndReloj(Usuario usuario, Reloj reloj);

    void deleteByUsuario(Usuario usuario);

    @Query("""
        SELECT COUNT(i)
        FROM Carrito i
        WHERE i.usuario = :usuario
    """)
    Long countProductosDistintos(Usuario usuario);

    @Query("""
            SELECT COALESCE(SUM(i.unidades),0)
            FROM Carrito i
            WHERE i.usuario = :usuario
            """)
    Long countUnidades(Usuario usuario);

    @Query("""
            SELECT COALESCE(SUM(i.unidades * (i.reloj.precio - i.reloj.descuento)),0)
            FROM Carrito i
            WHERE i.usuario = :usuario
            """)
    Double calcularImporteTotal(Usuario usuario);

}
