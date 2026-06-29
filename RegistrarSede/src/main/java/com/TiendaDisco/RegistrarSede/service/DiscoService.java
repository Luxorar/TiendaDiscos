package com.TiendaDisco.RegistrarSede.service;

import com.TiendaDisco.RegistrarSede.dto.DiscoDTO;
import com.TiendaDisco.RegistrarSede.exception.ManejoErrores;
import com.TiendaDisco.RegistrarSede.mapper.Mapper;
import com.TiendaDisco.RegistrarSede.model.Disco;
import com.TiendaDisco.RegistrarSede.repository.DiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoService implements IDiscoService {
    @Autowired
    private DiscoRepository repo;

    //==================REGISTRA UN DISCO================================
    public Disco postDisco(Disco d) {
        return repo.save(d);
    }

    //==================OBTIENE DISCO POR ID================================
    public DiscoDTO getDiscoId(Long id) {
        Disco disco = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrada"));
        return Mapper.toDTO(disco);
    }

    //==================OBTIENE TODOS LOS DISCOS================================
    public List<DiscoDTO> getAllDiscos() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    //==================MODIFICA UN DISCO================================
    public String putDisco(Long id, Disco d) {
        Disco disco = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a modificar no encontrada"));
        disco.setNombreDisco(d.getNombreDisco());
        disco.setArtista(d.getArtista());
        repo.save(disco);
        return "Datos del disco modificados";
    }

    //==================ELIMINA UN DISCO================================
    public String deleteDisco(Long id) {
        Disco disco = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Usuario no encontrado"));
        repo.delete(disco);
        return "Disco elimiando";
    }
}
