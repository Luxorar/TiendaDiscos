package com.TiendaDisco.RegistrarSede.service;


import com.TiendaDisco.RegistrarSede.model.Disco;
import com.TiendaDisco.RegistrarSede.model.Sede;

public interface ISedeService {

    Sede postSede(Sede s);

    Sede getSedeId(Long id);

    String putSede(Long id, Sede d);

    String deleteSedeId(Long id);
}
