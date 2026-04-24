package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.CategoriaReloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.CategoriaRelojService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoriaRelojService categoriaService;

    public DataLoader(CategoriaRelojService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Creamos las 5 categorías
        categoriaService.guardar(new CategoriaReloj(1L, "Elegante", "Relojes para ocasiones formales", "elegante.jpg"));
        categoriaService.guardar(new CategoriaReloj(2L, "Deportivo", "Resistentes al agua y con cronómetro", "deportivo.jpg"));
        categoriaService.guardar(new CategoriaReloj(3L, "Smartwatch", "Lo último en tecnología y salud", "smartwatch.jpg"));
        categoriaService.guardar(new CategoriaReloj(4L, "Casual", "Para el día a día, cómodos y versátiles", "casual.jpg"));

        // La 5ª categoría SIN imagen (le pasamos null)
        categoriaService.guardar(new CategoriaReloj(5L, "Coleccionista", "Ediciones limitadas y vintage", null));

        System.out.println("Categorías cargadas en memoria con éxito.");
    }
}
