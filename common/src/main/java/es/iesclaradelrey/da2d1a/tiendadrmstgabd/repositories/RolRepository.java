package es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, String> {
    // Se usa String porque el ID del Rol es 'ADMIN' o 'USER'
}