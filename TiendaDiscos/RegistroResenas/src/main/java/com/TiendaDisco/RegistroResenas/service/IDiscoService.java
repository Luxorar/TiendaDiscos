package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.model.Disco;

public interface IDiscoService{

    Disco postDisco(Disco d);

    Disco getDiscoId(Long id);

    String putDisco(Long id, Disco d);

    String deleteDisco(Long id);
}
