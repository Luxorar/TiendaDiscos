package com.TiendaDisco.AdministracionEnvios.service;

import com.TiendaDisco.AdministracionEnvios.DTO.EnvioDTO;
import com.TiendaDisco.AdministracionEnvios.exception.ManejoErrores;
import com.TiendaDisco.AdministracionEnvios.mapper.Mapper;
import com.TiendaDisco.AdministracionEnvios.model.Envio;
import com.TiendaDisco.AdministracionEnvios.model.EstadoEnvio;
import com.TiendaDisco.AdministracionEnvios.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementacion del servicio de envios.
 * Contiene la logica de negocio para gestionar los envios del sistema.
 */
@Service
public class EnvioService implements IEnvioService{
    @Autowired
    private EnvioRepository repo;

    @Autowired
    private Mapper mapper;

    /**
     * Registra un nuevo envio en la base de datos.
     *
     * @param envio datos del envio
     * @return el envio persistido
     */
    @Override
    public Envio postEnvio(Envio envio) {
        return repo.save(envio);
    }

    /**
     * Obtiene todos los envios y los convierte a DTO.
     *
     * @return lista de EnvioDTO
     */
    @Override
    public List<EnvioDTO> getAllEnvios() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    /**
     * Actualiza el estado de un envio, lanzando excepcion si no existe.
     *
     * @param estado nuevo estado
     * @param id     identificador del envio
     * @return el envio con el estado actualizado
     */
    @Override
    public Envio PutEstadoEnvio(EstadoEnvio estado, Long id) {
        Envio e = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Envio no encontrado"));

        e.setEstadoEnvio(estado);
        return repo.save(e);
    }

    /**
     * Actualiza la direccion de destino de un envio.
     *
     * @param direccion nueva direccion
     * @param id        identificador del envio
     * @return el envio con la direccion actualizada
     */
    @Override
    public Envio PutDirEnvio(String direccion, Long id) {
        Envio e = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Envio no encontrado"));

        e.setDireccionDestino(direccion);
        return repo.save(e);
    }

    /**
     * Elimina un envio si existe, lanzando excepcion en caso contrario.
     *
     * @param id identificador del envio
     */
    @Override
    public void deleteEnvio(Long id) {
        if(!repo.existsById(id)){
            throw new ManejoErrores("No existe un envio con este id");
        }

        repo.deleteById(id);
    }
}
