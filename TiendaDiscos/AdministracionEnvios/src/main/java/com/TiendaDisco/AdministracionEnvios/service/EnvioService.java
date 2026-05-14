package com.TiendaDisco.AdministracionEnvios.service;

import com.TiendaDisco.AdministracionEnvios.exception.ManejoErrores;
import com.TiendaDisco.AdministracionEnvios.model.Envio;
import com.TiendaDisco.AdministracionEnvios.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EnvioService implements IEnvioService{
    @Autowired
    private EnvioRepository repo;

    @Override
    public Envio postEnvio(Envio envio) {
        return repo.save(envio);
    }

    @Override
    public List<Envio> getAllEnvios() {
        return repo.findAll().stream().toList();
    }

    @Override
    public Envio PutEstadoEnvio(Envio envio, Long id) {
        Envio e = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Envio no encontrado"));

        e.setVentaId(envio.getVentaId());
        e.setDireccionDestino(envio.getDireccionDestino());
        e.setTipoDespacho(envio.getTipoDespacho());
        e.setEmpresaReparto(envio.getEmpresaReparto());
        e.setEstadoEnvio(envio.getEstadoEnvio());
        e.setFechaEntrega(envio.getFechaEntrega());
        return envio;
    }

    @Override
    public Envio PutDirEnvio(String direccion, Long id) {
        Envio e = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Envio no encontrado"));

        e.setDireccionDestino(direccion);
        return e;
    }

    @Override
    public void deleteEnvio(Long id) {
        if(!repo.existsById(id)){
            throw new ManejoErrores("No existe un envio con este id");
        }

        repo.deleteById(id);
    }
}
