package com.TiendaDisco.RegistrarSede.service;


import com.TiendaDisco.RegistrarSede.dto.SedeDTO;
import com.TiendaDisco.RegistrarSede.model.Sede;

import java.util.List;

public interface ISedeService {

    Sede postSede(Sede s);

    List<SedeDTO> getAllSedes();

    SedeDTO getSedeId(Long id);

    String putSede(Long id, Sede d);

    String deleteSedeId(Long id);
}
