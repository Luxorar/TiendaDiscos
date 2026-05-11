package com.TiendaDisco.AdministracionEnvios.service;

import com.TiendaDisco.AdministracionEnvios.model.Envio;
import com.TiendaDisco.AdministracionEnvios.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class EnvioService implements IEnvioService{
    @Autowired
    private EnvioRepository repo;

    @Override
    public Envio postEnvio(Envio envio) {
        return null;
    }

    @Override
    public ArrayList<Envio> getAllEnvios() {
        return null;
    }

    @Override
    public Envio PutEstadoEnvio(Envio envio, Long id) {
        return null;
    }

    @Override
    public Envio PutDirEnvio(Envio envio, Long id) {
        return null;
    }

    @Override
    public Envio deleteEnvio(Long id) {
        return null;
    }
}
