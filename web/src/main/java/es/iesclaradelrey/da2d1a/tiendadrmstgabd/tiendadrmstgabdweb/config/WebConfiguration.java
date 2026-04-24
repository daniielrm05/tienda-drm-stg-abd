package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Clase de configuración web
// Permite registrar rutas que devuelven vistas directamente sin necesitar un controlador
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Ruta /condiciones devuelve la vista terms.html sin pasar por un @Controller
        registry.addViewController("/condiciones").setViewName("terms");
    }

}
