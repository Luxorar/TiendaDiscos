package com.TiendaDiscos.Direcciones.service;

import com.TiendaDiscos.Direcciones.model.Direccion;
import com.TiendaDiscos.Direcciones.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DireccionService {
    @Autowired
    private DireccionRepository direccionRepository;

    public String postDireccion(Direccion direc) {
        return direccionRepository.postDireccion(direc);
    }

    public ArrayList<Direccion> getCiudadPais(String ciudad, String pais) {
        return direccionRepository.getCiudadPais(ciudad, pais);
    }

    public Direccion getDireccionId(Long id) {
        return direccionRepository.getDireccionId(id);
    }

    public Direccion putDireccion(Long id, Direccion direc) {
        return direccionRepository.putDireccion(id, direc);
    }

    public String DeleteDireccion(Long id) {
        return direccionRepository.DeleteDireccion(id);
    }
}
