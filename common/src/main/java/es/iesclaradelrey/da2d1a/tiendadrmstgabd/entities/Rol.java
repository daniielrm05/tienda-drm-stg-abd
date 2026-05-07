package es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rol {

    @Id
    @Column(length = 6)
    private String id;

    @Column(length = 100)
    private String descripcion;

}
