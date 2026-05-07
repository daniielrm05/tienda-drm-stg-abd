package es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.service;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.TipoEventoSeguridad;
import org.springframework.stereotype.Service;

@Service
public interface EventoSeguridadService {
    void registrar(String username, TipoEventoSeguridad tipoEventoSeguridad);
}
