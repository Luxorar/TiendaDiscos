package com.TiendaDisco.AdministracionEnvios.service;

import com.TiendaDisco.AdministracionEnvios.model.Envio;
import com.TiendaDisco.AdministracionEnvios.model.EstadoEnvio;

import java.util.List;

public interface IEnvioService {

    Envio postEnvio(Envio envio);

    List<Envio> getAllEnvios();

    Envio PutEstadoEnvio(EstadoEnvio estado, Long id);

    Envio PutDirEnvio(String direccion, Long id);

    void deleteEnvio(Long id);
}
