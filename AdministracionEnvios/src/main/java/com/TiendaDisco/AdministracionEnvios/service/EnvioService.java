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
 * <p>Contiene la logica de negocio para registrar, consultar, actualizar
 * y eliminar envios asociados a las ventas.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Service
public class EnvioService implements IEnvioService{
    @Autowired
    private EnvioRepository repo;

    @Autowired
    private Mapper mapper;

    @Override
    public Envio postEnvio(Envio envio) {
        return repo.save(envio);
    }

    @Override
    public List<EnvioDTO> getAllEnvios() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    @Override
    public Envio PutEstadoEnvio(EstadoEnvio estado, Long id) {
        Envio e = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Envio no encontrado"));

        e.setEstadoEnvio(estado);
        return repo.save(e);
    }

    @Override
    public Envio PutDirEnvio(String direccion, Long id) {
        Envio e = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Envio no encontrado"));

        e.setDireccionDestino(direccion);
        return repo.save(e);
    }

    @Override
    public void deleteEnvio(Long id) {
        if(!repo.existsById(id)){
            throw new ManejoErrores("No existe un envio con este id");
        }

        repo.deleteById(id);
    }
}
