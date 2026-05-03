package es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity                                                 //indica que es una entidad JPA
@AllArgsConstructor                                     //constructor con todos los argumentos
@NoArgsConstructor                                      //constructor vacío
@Getter                                                 //genera getters
@Setter                                                 //genera setters
@EqualsAndHashCode(onlyExplicitlyIncluded = true)       //equals y hashCode con los argumentos que indiquemos
public class Reloj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id autogenerado
    @EqualsAndHashCode.Include // Solo el ID define la igualdad
    private Long id;

    @NotBlank                                           //no permite null ni vacío
    @Pattern(regexp = "\\d{13}", message = "El EAN debe tener exactamente 13 dígitos")
    //exactamente 13 dígitos (EAN-13)
    @Column(length = 13, unique = true)                 //longitud fija 13 y único en la BD
    private String codigo;

    @NotBlank                                           //obligatorio
    @Size(max = 200)                                    //máximo 200 caracteres
    private String nombre;

    @NotBlank                                           //obligatorio
    @Size(max = 4000)                                   //máximo 4000 caracteres
    private String descripcion;

    @Size(max = 500)                                    //máximo 500 caracteres
    private String imagen;

    @NotNull                                            //obligatorio
    @Positive                                           //solo valores positivos
    private Double precio;

    @NotNull                                            //obligatorio
    @Min(0)                                             //mínimo 0
    @Max(99)                                            //máximo 99
    private Integer descuento;

    @ManyToOne(optional = false)                        //muchos relojes pertenecen a una marca
                                                        //optional = false → obligatorio (siempre tiene marca)
    @JoinColumn(name = "marca_id", nullable = false)    //clave foránea en la tabla reloj
    private Marca marca;

    @ManyToMany(fetch = FetchType.EAGER)                //muchos relojes pueden tener muchas categorías y una categoría puede tener muchos relojes
    @JoinTable(
            name = "reloj_categoria",                   //tabla intermedia
            joinColumns = @JoinColumn(name = "reloj_id"),        //FK hacia reloj
            inverseJoinColumns = @JoinColumn(name = "categoria_id") //FK hacia categoría
    )
    private List<CategoriaReloj> categorias;

}
