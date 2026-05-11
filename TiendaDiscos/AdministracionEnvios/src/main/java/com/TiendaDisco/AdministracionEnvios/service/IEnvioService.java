package com.TiendaDisco.AdministracionEnvios.service;

import com.TiendaDisco.AdministracionEnvios.model.Envio;

import java.util.ArrayList;

public interface IEnvioService {

    Envio postEnvio(Envio envio);

    ArrayList<Envio> getAllEnvios();

    Envio PutEstadoEnvio(Envio envio, Long id);

    Envio PutDirEnvio(Envio envio, Long id);

    Envio deleteEnvio(Long id);
}
