package es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.handler;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.TipoEventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.service.EventoSeguridadService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginExitoso implements AuthenticationSuccessHandler {

    private final EventoSeguridadService service;

    public LoginExitoso(EventoSeguridadService service) {
        this.service = service;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException {

        service.registrar(
                authentication.getName(),
                TipoEventoSeguridad.LOGIN_OK
        );

        response.sendRedirect("/");
    }
}