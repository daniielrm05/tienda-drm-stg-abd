package es.iesclaradelrey.da2d1a.tiendadrmstgabd.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos.CarritoAnadirDto;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos.CarritoResponseDto;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Carrito;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Reloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.exceptions.CarritoException;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.exceptions.ResourceNotFoundException;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.mappers.CarritoMapper;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.CarritoService;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.RelojService;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
public class CarritoController {

    private final CarritoService carritoService;
    private final RelojService relojService;
    private final UsuarioService usuarioService;
    private final CarritoMapper carritoMapper;

    public CarritoController(CarritoService carritoService,
                             RelojService relojService,
                             UsuarioService usuarioService,
                             CarritoMapper carritoMapper) {
        this.carritoService = carritoService;
        this.relojService = relojService;
        this.usuarioService = usuarioService;
        this.carritoMapper = carritoMapper;
    }

    // Obtiene el usuario autenticado del contexto de seguridad
    private Usuario getUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return usuarioService.buscarPorUsername(auth.getName()).orElseThrow();
    }

    // Construye el CarritoResponseDto con todos los datos del carrito
    private CarritoResponseDto buildRespuesta(Usuario usuario) {
        List<Carrito> items = carritoService.buscarPorUsuario(usuario);
        Double total = carritoService.calcularImporteTotal(usuario);
        return carritoMapper.toResponseDto(items, total != null ? total : 0.0);
    }

    // Añadir producto al carrito
    @PostMapping
    public ResponseEntity<CarritoResponseDto> añadir(@RequestBody CarritoAnadirDto peticion) {
        Usuario usuario = getUsuarioAutenticado();

        // Verificar que el reloj existe
        Reloj reloj = relojService.buscarPorId(peticion.getRelojId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El reloj con ID " + peticion.getRelojId() + " no existe"));

        // Verificar que las unidades son positivas
        if (peticion.getUnidades() <= 0) {
            throw new CarritoException("Las unidades deben ser mayores que cero");
        }

        // Verificar stock suficiente
        if (reloj.getStock() < peticion.getUnidades()) {
            throw new CarritoException("Stock insuficiente para el reloj " + reloj.getNombre());
        }

        // Si ya está en el carrito, incrementar unidades
        Optional<Carrito> itemExistente = carritoService.buscarPorUsuarioYReloj(usuario, reloj);
        if (itemExistente.isPresent()) {
            Carrito item = itemExistente.get();
            item.setUnidades(item.getUnidades() + peticion.getUnidades());
            item.setFecha(LocalDate.now());
            carritoService.guardar(item);
        } else {
            // Si no está, añadirlo
            Carrito nuevoItem = new Carrito();
            nuevoItem.setUsuario(usuario);
            nuevoItem.setReloj(reloj);
            nuevoItem.setUnidades(peticion.getUnidades());
            nuevoItem.setFecha(LocalDate.now());
            carritoService.guardar(nuevoItem);
        }

        return ResponseEntity.ok(buildRespuesta(usuario));
    }

    // Listar el carrito del usuario autenticado
    @GetMapping
    public ResponseEntity<CarritoResponseDto> listar() {
        Usuario usuario = getUsuarioAutenticado();
        return ResponseEntity.ok(buildRespuesta(usuario));
    }

    // Eliminar un producto del carrito
    @DeleteMapping("/{productoId}")
    public ResponseEntity<CarritoResponseDto> eliminar(@PathVariable Long productoId) {
        Usuario usuario = getUsuarioAutenticado();

        // Verificar que el reloj existe
        Reloj reloj = relojService.buscarPorId(productoId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El reloj con ID " + productoId + " no existe"));

        // Verificar que el reloj está en el carrito
        Carrito item = carritoService.buscarPorUsuarioYReloj(usuario, reloj)
                .orElseThrow(() -> new CarritoException(
                        "El reloj " + reloj.getNombre() + " no está en el carrito"));

        carritoService.eliminar(item);

        return ResponseEntity.ok(buildRespuesta(usuario));
    }

    // Vaciar el carrito
    @DeleteMapping
    public ResponseEntity<CarritoResponseDto> vaciar() {
        Usuario usuario = getUsuarioAutenticado();
        carritoService.vaciar(usuario);
        return ResponseEntity.ok(buildRespuesta(usuario));
    }
}