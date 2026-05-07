package es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Busca un usuario por su email
    Optional<Usuario> findByEmail(String email);

    //Busca un usuario por su username
    Optional<Usuario> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}