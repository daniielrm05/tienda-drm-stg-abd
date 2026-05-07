package es.iesclaradelrey.da2d1a.tiendadrmstgabd.services;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UsuarioServiceImplementation implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImplementation(UsuarioRepository repository) {
        this.repository = repository;
    }

    // Devuelve todos los usuarios
    @Override
    public Collection<Usuario> buscarTodo() {
        return repository.findAll();
    }

    // Busca un usuario por su id
    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Busca un usuario por su email
    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    // Busca un usuario por su username
    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        return repository.findByUsername(username);
    }

    // Guarda un usuario — lanza excepción si ya existe el email o el username
    @Override
    public void guardar(Usuario usuario) {
        Optional<Usuario> existentePorEmail = repository.findByEmail(usuario.getEmail());
        if (existentePorEmail.isPresent()) {
            if (usuario.getId() == null || !usuario.getId().equals(existentePorEmail.get().getId())) {
                throw new RuntimeException("Ya existe un usuario con ese email");
            }
        }

        Optional<Usuario> existentePorUsername = repository.findByUsername(usuario.getUsername());
        if (existentePorUsername.isPresent()) {
            if (usuario.getId() == null || !usuario.getId().equals(existentePorUsername.get().getId())) {
                throw new RuntimeException("Ya existe un usuario con ese nombre de usuario");
            }
        }

        repository.save(usuario);
    }

    // Elimina un usuario por su id
    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}