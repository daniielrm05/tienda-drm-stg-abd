package es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class EventoSeguridad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    private TipoEventoSeguridad tipo;

    public EventoSeguridad(String username, TipoEventoSeguridad tipo) {
        this.username = username;
        this.tipo = tipo;
        this.fechaHora = LocalDateTime.now();
    }

}