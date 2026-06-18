package com.TiendaDisco.RegistrarDiscos.service;

import java.util.List;
import com.TiendaDisco.RegistrarDiscos.dto.DiscoDTO;
import com.TiendaDisco.RegistrarDiscos.model.Disco;

public interface IDiscoService {
    Disco postDisco(Disco d);

    DiscoDTO getDiscoId(Long id);

    String putDisco(Long id, Disco d);

    String deleteDisco(Long id);

    List<DiscoDTO> getAllDiscos();
}
