package es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.JwtAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

        // 1. Desactivar CSRF (Requisito para APIs REST con JWT)
        .csrf(AbstractHttpConfigurer::disable)

        // 2. Sesiones STATELESS: No se guarda sesión en el servidor
        .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )

        // 3. Configuración de URLs permitidas (Whitelisting)
        .authorizeHttpRequests(auth -> auth
                // Permitimos las URLs de autenticación (Login y Refresh)
                // Estas se usaran controlador de Auth
                // Única puerta abierta: para loguearse
                .requestMatchers("/api/v1/auth/**").permitAll()

                // Puerta cerrada para el resto: categorías, productos, carrito...
                // Si no envían el "Authorization: Bearer <token>", Spring lanza el 401
                .anyRequest().authenticated()
        )

        .authenticationProvider(authenticationProvider)
        // AÑADIR EL FILTRO JWT ANTES DEL DE USUARIO/PASSWORD
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
