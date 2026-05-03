package es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity                                                 //indica que es una entidad JPA
@AllArgsConstructor                                     //constructor con todos los argumentos
@NoArgsConstructor                                      //constructor vacío
@Getter                                                 //genera getters
@Setter                                                 //genera setters
@EqualsAndHashCode(onlyExplicitlyIncluded = true)       //equals y hashCode con los argumentos que indiquemos
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id autogenerado (auto-increment)
    @EqualsAndHashCode.Include // Solo el ID define la igualdad
    private Long id;

    @NotBlank                                           //no permite null ni cadena vacía
    @Size(max = 100)                                    //máximo 100 caracteres
    @Column(unique = true)                              //no puede haber dos marcas con el mismo nombre
    private String nombre;

    @OneToMany(mappedBy = "marca")                      //relación 1 a muchos con Reloj
                                                        //una marca puede tener muchos relojes
                                                        //mappedBy indica que la relación se gestiona desde Reloj
    private Set<Reloj> relojes;

}
