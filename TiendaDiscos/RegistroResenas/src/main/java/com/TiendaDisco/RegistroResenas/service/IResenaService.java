package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.model.Resena;

public interface IResenaService {

    Resena postResena(Resena r);

    Resena getResenaId(Long id);

    String deleteResena(Long id);
}
