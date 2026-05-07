package es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.config;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.handler.LoginExitoso;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.handler.LogoutSuccessHandlerImpl;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.handler.LoginFallido;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           LoginExitoso successHandler,
                                           LoginFallido failureHandler,
                                           LogoutSuccessHandlerImpl logoutHandler) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(successHandler)
                        .failureHandler(failureHandler)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(logoutHandler)
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        return http.build();
    }
}
