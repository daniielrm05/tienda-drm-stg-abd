package es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

/**
 * Define las categorías principales de la tienda de relojes.
 */

@Entity
@Table(name = "categoria_reloj", uniqueConstraints = {     //constraint que impide que haya 2 categorias_reloj
        @UniqueConstraint(columnNames = "nombre")          //con el mismo nombre
})
@AllArgsConstructor                                        //constructor con todos los argumentos
@NoArgsConstructor                                         //constructor con argumentos vacíos
@Getter                                                 //genera getters
@Setter                                                 //genera setters
@EqualsAndHashCode(onlyExplicitlyIncluded = true)       //equals y hashCode con los argumentos que indiquemos
public class CategoriaReloj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //id auotogenerado
    private Long id;

    @NotBlank                                               //no acepta cadena vacía ni nula
    @Size(max = 100)                                        //acepta hasta un máximo de 100 caracteres
    private String nombre;

    @Size(max = 2000)                                       //acepta hasta un máximo de 2000 caracteres
    private String descripcion;

    @Size(max = 500)                                        //acepta hasta un máximo de 500 caracteres
    private String imagen;

    @ManyToMany(mappedBy = "categorias")                    //relación muchos a muchos con Reloj
                                                            //una categoría puede tener muchos relojes
                                                            //mappedBy indica que la tabla intermedia se define en Reloj
    private Set<Reloj> relojes;

}