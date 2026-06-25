package com.TiendaDisco.RegistrarSede.service;

import com.TiendaDisco.RegistrarSede.dto.DiscoDTO;
import com.TiendaDisco.RegistrarSede.model.Disco;

import java.util.List;

public interface IDiscoService {
    Disco postDisco(Disco d);
    List<DiscoDTO> getAllDiscos();
    DiscoDTO getDiscoId(Long id);
    String putDisco(Long id, Disco d);
    String deleteDisco(Long id);
}
