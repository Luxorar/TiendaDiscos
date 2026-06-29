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


@Service
public class EnvioService implements IEnvioService{
    @Autowired
    private EnvioRepository repo;

    @Autowired
    private Mapper mapper;

    //==================REGISTRA UN ENVIO================================
    @Override
    public Envio postEnvio(Envio envio) {
        return repo.save(envio);
    }

    //==================OBTIENE TODOS LOS ENVIOS================================
    @Override
    public List<EnvioDTO> getAllEnvios() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    //==================CAMBIA EL ESTADO DE UN ENVIO================================
    @Override
    public Envio PutEstadoEnvio(EstadoEnvio estado, Long id) {
        Envio e = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Envio no encontrado"));

        e.setEstadoEnvio(estado);
        return repo.save(e);
    }

    //==================CAMBIA LA DIRECCION DE UN ENVIO================================
    @Override
    public Envio PutDirEnvio(String direccion, Long id) {
        Envio e = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Envio no encontrado"));

        e.setDireccionDestino(direccion);
        return repo.save(e);
    }

    //==================ELIMINA UN ENVIO================================
    @Override
    public void deleteEnvio(Long id) {
        if(!repo.existsById(id)){
            throw new ManejoErrores("No existe un envio con este id");
        }

        repo.deleteById(id);
    }
}
