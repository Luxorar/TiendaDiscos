package com.TiendaDisco.AdministracionEnvios.service;

import com.TiendaDisco.AdministracionEnvios.DTO.EnvioDTO;
import com.TiendaDisco.AdministracionEnvios.model.Envio;
import com.TiendaDisco.AdministracionEnvios.model.EstadoEnvio;

import java.util.List;

/**
 * Servicio de logica de negocio para envios.
 * Define las operaciones disponibles para gestionar envios.
 */
public interface IEnvioService {

    /**
     * Registra un nuevo envio en el sistema.
     *
     * @param envio datos del envio a crear
     * @return el envio registrado
     */
    Envio postEnvio(Envio envio);

    /**
     * Obtiene todos los envios registrados.
     *
     * @return lista de {@link EnvioDTO}
     */
    List<EnvioDTO> getAllEnvios();

    /**
     * Actualiza el estado de un envio existente.
     *
     * @param estado nuevo estado del envio
     * @param id     identificador del envio
     * @return el envio actualizado
     */
    Envio PutEstadoEnvio(EstadoEnvio estado, Long id);

    /**
     * Actualiza la direccion de destino de un envio.
     *
     * @param direccion nueva direccion de destino
     * @param id        identificador del envio
     * @return el envio actualizado
     */
    Envio PutDirEnvio(String direccion, Long id);

    /**
     * Elimina un envio por su identificador.
     *
     * @param id identificador del envio a eliminar
     */
    void deleteEnvio(Long id);
}
