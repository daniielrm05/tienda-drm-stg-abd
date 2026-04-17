package es.iesclaradelrey.da2d1a.tiendadrmstgadb.entitie;

import lombok.Getter;

/**
 * Define las categorías principales de la tienda de relojes.
 */

public class CategoriaReloj {
    private Long id;
    private String nombre;
    private String descripcion;
    private String imagen;

    // Constructor vacío
    public CategoriaReloj() {}

    // Constructor con parámetros (te servirá para el CommandLineRunner)
    public CategoriaReloj(Long id, String nombre, String descripcion, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "CategoriaReloj{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}