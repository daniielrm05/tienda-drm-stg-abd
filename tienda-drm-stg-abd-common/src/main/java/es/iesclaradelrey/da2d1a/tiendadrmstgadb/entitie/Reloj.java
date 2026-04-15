package es.iesclaradelrey.da2d1a.tiendadrmstgadb.entitie;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

/**
 * Entidad que representa un producto de tipo Reloj en la tienda.
 * Ubicada en el módulo COMMON para ser compartida.
 */
@Entity
@Table(name = "relojes")
@Data // Genera Getters, Setters, ToString... (Requiere dependencia Lombok)
public class Reloj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal precio;

    @Min(0)
    private Integer stock;

    @Enumerated(EnumType.STRING)
    private CategoriaReloj categoria; // ELEGANTE, DEPORTE, SMARTWATCH

    // Atributos específicos
    private String materialCorrea;
    private String resistenciaAgua; // Ej: 50m, 10ATM

    // Para Smartwatch (pueden ser nulos si es elegante)
    private String sistemaOperativo;
    private Boolean tieneSensorRitmoCardiaco;
}