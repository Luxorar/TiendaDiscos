package com.TiendaDisco.RegistrarDiscos.service;

import com.TiendaDisco.RegistrarDiscos.model.Disco;

public interface IDiscoService {
    Disco postDisco(Disco d);

    Disco getDiscoId(Long id);

    String deleteDisco(Long id);

    String putDisco(Long id, Disco d);
}
