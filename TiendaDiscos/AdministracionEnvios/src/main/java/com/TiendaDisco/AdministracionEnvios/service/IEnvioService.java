package com.TiendaDisco.AdministracionEnvios.service;

import com.TiendaDisco.AdministracionEnvios.model.Envio;

import java.util.List;

public interface IEnvioService {

    Envio postEnvio(Envio envio);

    List<Envio> getAllEnvios();

    Envio PutEstadoEnvio(Envio envio, Long id);

    Envio PutDirEnvio(String direccion, Long id);

    void deleteEnvio(Long id);
}
