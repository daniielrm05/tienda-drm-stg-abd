package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "es.iesclaradelrey.da2d1a")
@EnableJpaRepositories(basePackages = "es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories")

@EntityScan(basePackages = "es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities")

public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
