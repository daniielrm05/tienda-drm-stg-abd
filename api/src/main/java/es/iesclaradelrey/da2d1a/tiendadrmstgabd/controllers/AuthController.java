package es.iesclaradelrey.da2d1a.tiendadrmstgabd.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos.LoginPeticionDto;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos.LoginRespuestaDto;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos.RefreshPeticionDto;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos.RefreshRespuestaDto;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.security.model.UsuarioDetails;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    // Login: recibe usuario y contraseña, devuelve access token y refresh token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginPeticionDto peticion) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            peticion.getUsername(),
                            peticion.getPassword()
                    )
            );

            UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();

            LoginRespuestaDto respuesta = new LoginRespuestaDto();
            respuesta.setAccessToken(jwtService.generateAccessToken(usuarioDetails));
            respuesta.setRefreshToken(jwtService.generateRefreshToken(usuarioDetails));

            return ResponseEntity.ok(respuesta);

        } catch (BadCredentialsException e) {
            // Credenciales incorrectas → 401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // Refresh: recibe refresh token, devuelve nuevo access token
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshPeticionDto peticion) {
        try {
            String username = jwtService.extractUsername(peticion.getRefreshToken());
            UsuarioDetails usuarioDetails = (UsuarioDetails) userDetailsService.loadUserByUsername(username);

            if (!jwtService.isTokenValid(peticion.getRefreshToken(), usuarioDetails)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            RefreshRespuestaDto respuesta = new RefreshRespuestaDto();
            respuesta.setAccessToken(jwtService.generateAccessToken(usuarioDetails));

            return ResponseEntity.ok(respuesta);

        } catch (Exception e) {
            // Token inválido o manipulado → 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}