package es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.handler;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.TipoEventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.service.EventoSeguridadService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private final EventoSeguridadService service;

    public LogoutSuccessHandlerImpl(EventoSeguridadService service) {
        this.service = service;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException {

        if (authentication != null) {
            service.registrar(
                    authentication.getName(),
                    TipoEventoSeguridad.LOGOUT
            );
        }

        response.sendRedirect("/");
    }
}
