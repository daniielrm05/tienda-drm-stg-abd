package es.iesclaradelrey.da2d1a.tiendadrmstgadb.entitie;

import lombok.Getter;

/**
 * Define las categorías principales de la tienda de relojes.
 */
@Getter
public enum CategoriaReloj {
    ELEGANTE("Elegante y Formal"),
    DEPORTE("Deportivo y Resistente"),
    SMARTWATCH("Reloj Inteligente"),
    CASUAL("Uso Diario"),
    COLECCIONISTA("Edición Limitada");

    private final String nombreVisible;

    CategoriaReloj(String nombreVisible) {
        this.nombreVisible = nombreVisible;
    }
}