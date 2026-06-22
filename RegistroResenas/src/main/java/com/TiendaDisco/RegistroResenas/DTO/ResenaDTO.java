package com.TiendaDisco.RegistroResenas.DTO;

import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResenaDTO {
    private Long id;
    private String userName;
    private String nombreDisco;
    private String mensaje;
}
