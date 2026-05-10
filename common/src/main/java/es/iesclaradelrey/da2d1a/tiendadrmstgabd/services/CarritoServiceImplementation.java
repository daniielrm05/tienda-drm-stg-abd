package es.iesclaradelrey.da2d1a.tiendadrmstgabd.services;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Carrito;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Reloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories.CarritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoServiceImplementation implements CarritoService {

    private final CarritoRepository repository;

    public CarritoServiceImplementation(CarritoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Carrito> buscarPorUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }

    @Override
    public Optional<Carrito> buscarPorUsuarioYReloj(Usuario usuario, Reloj reloj) {
        return repository.findByUsuarioAndReloj(usuario, reloj);
    }

    @Override
    public Carrito guardar(Carrito item) {
        return repository.save(item);
    }

    @Override
    public void eliminar(Carrito item) {
        repository.delete(item);
    }

    @Override
    public void vaciar(Usuario usuario) {
        repository.deleteByUsuario(usuario);
    }

    @Override
    public Long contarProductosDistintos(Usuario usuario) {
        return repository.countProductosDistintos(usuario);
    }

    @Override
    public Long contarUnidades(Usuario usuario) {
        return repository.countUnidades(usuario);
    }

    @Override
    public Double calcularImporteTotal(Usuario usuario) {
        return repository.calcularImporteTotal(usuario);
    }
}
