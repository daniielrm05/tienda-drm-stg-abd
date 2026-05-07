package es.iesclaradelrey.da2d1a.tiendadrmstgabd.services;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Usuario;

import java.util.Collection;
import java.util.Optional;

public interface UsuarioService {
    Collection<Usuario> buscarTodo();
    Optional<Usuario> buscarPorId(Long id);
    Optional<Usuario> buscarPorEmail(String email);
    Optional<Usuario> buscarPorUsername(String username);
    void guardar(Usuario usuario);
    void eliminar(Long id);
}