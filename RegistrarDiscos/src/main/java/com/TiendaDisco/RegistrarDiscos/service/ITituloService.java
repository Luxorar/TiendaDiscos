package com.TiendaDisco.RegistrarDiscos.service;

import com.TiendaDisco.RegistrarDiscos.model.Titulo;

public interface ITituloService {
    Titulo postTitulo(Titulo t);

    Titulo getTituloId(Long id);

    String putTitulo(Long id, Titulo t);

    String deleteTitulo(Long id);
}