package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.model.Disco;

import java.util.List;

public interface IDiscoService{

    Disco postDisco(Disco d);

    List<Disco> getAllDiscos();

    Disco getDiscoId(Long id);

    String putDisco(Long id, Disco d);

    String deleteDisco(Long id);
}
