package es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity                                                 //indica que es una entidad JPA
@AllArgsConstructor                                     //constructor con todos los argumentos
@NoArgsConstructor                                      //constructor vacío
@Data                                                   //genera getters, setters y toString
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id autogenerado (auto-increment)
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
