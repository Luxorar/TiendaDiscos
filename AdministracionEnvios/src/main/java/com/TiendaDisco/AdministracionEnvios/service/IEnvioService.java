package com.TiendaDisco.AdministracionEnvios.service;

import com.TiendaDisco.AdministracionEnvios.DTO.EnvioDTO;
import com.TiendaDisco.AdministracionEnvios.model.Envio;
import com.TiendaDisco.AdministracionEnvios.model.EstadoEnvio;

import java.util.List;

/**
 * Interface que define las operaciones de negocio para envios.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public interface IEnvioService {

    Envio postEnvio(Envio envio);

    List<EnvioDTO> getAllEnvios();

    Envio PutEstadoEnvio(EstadoEnvio estado, Long id);

    Envio PutDirEnvio(String direccion, Long id);

    void deleteEnvio(Long id);
}
