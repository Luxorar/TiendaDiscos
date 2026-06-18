package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.model.Resena;

import java.util.List;

public interface IResenaService {

    Resena postResena(Resena r);

    List<Resena> getAllResenas();

    Resena getResenaId(Long id);

    String deleteResena(Long id);
}
