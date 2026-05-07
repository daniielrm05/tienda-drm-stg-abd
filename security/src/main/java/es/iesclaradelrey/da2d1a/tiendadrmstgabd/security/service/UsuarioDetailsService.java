package es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.service;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories.UsuarioRepository;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.model.UsuarioDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new UsuarioDetails(usuario);
    }
}