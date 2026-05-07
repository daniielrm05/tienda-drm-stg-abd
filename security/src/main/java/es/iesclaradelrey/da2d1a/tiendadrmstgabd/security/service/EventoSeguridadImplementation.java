package es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.service;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.EventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.TipoEventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories.EventoSeguridadRepository;
import org.springframework.stereotype.Service;

@Service
public class EventoSeguridadImplementation implements EventoSeguridadService {

    private final EventoSeguridadRepository repository;

    public EventoSeguridadImplementation(EventoSeguridadRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registrar(String username, TipoEventoSeguridad tipoEventoSeguridad) {
        repository.save(new EventoSeguridad(username, tipoEventoSeguridad));
    }
}
