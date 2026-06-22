package com.TiendaDisco.RegistroResenas.DTO;

import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscoDTO {
    private Long id;
    private String nombreDisco;
    private String artista;
}
