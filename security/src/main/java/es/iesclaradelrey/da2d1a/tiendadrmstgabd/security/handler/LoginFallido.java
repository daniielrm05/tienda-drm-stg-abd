package es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.handler;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.TipoEventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.service.EventoSeguridadService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginFallido implements AuthenticationFailureHandler {

    private final EventoSeguridadService service;

    public LoginFallido(EventoSeguridadService service) {
        this.service = service;
    }


    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {

        String username = request.getParameter("username");

        service.registrar(
                username,
                TipoEventoSeguridad.LOGIN_ERROR
        );

        response.sendRedirect("/login?error");

    }
}
