package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.DTO.ResenaDTO;
import com.TiendaDisco.RegistroResenas.model.Resena;

import java.util.List;

public interface IResenaService {

    Resena postResena(Resena r);

    List<ResenaDTO> getAllResenas();

    ResenaDTO getResenaId(Long id);

    String deleteResena(Long id);
}
