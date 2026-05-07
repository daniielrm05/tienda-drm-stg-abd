package es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.service;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.TipoEventoSeguridad;

public interface EventoSeguridadService {
    void registrar(String username, TipoEventoSeguridad tipoEventoSeguridad);
}
