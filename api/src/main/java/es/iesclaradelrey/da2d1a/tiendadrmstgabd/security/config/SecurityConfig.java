package es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitamos CSRF porque para APIs REST suele estorbar (se verá más adelante)
                .csrf(AbstractHttpConfigurer::disable)

                // Configuramos las reglas de autorización
                .authorizeHttpRequests(auth -> auth
                        // Permitimos el acceso a CUALQUIER petición sin autenticación
                        .anyRequest().permitAll()
                );

        return http.build();
    }

}
