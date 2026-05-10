package es.iesclaradelrey.da2d1a.tiendadrmstgabd.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRespuestaDto {
    private String accessToken;
    private String refreshToken;
}