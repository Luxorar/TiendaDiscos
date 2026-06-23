package com.TiendaDisco.AdministracionDescuentos.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class DiscoDTO {
    private Long id;

    private String nombreDisco;

    private String artista;

    private int precio;
}
