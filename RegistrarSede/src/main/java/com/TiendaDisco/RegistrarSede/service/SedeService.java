package com.TiendaDisco.RegistrarSede.service;

import com.TiendaDisco.RegistrarSede.dto.SedeDTO;
import com.TiendaDisco.RegistrarSede.exception.ManejoErrores;
import com.TiendaDisco.RegistrarSede.mapper.Mapper;
import com.TiendaDisco.RegistrarSede.model.Sede;
import com.TiendaDisco.RegistrarSede.repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SedeService implements ISedeService{
    @Autowired
    private SedeRepository repo;

    public Sede postSede(Sede s){
        return repo.save(s);
    }

    public SedeDTO getSedeId(Long id){
        Sede sede = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrado"));
        return Mapper.toDTO(sede);
    }

    public List<SedeDTO> getAllSedes(){
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    public String putSede(Long id, Sede s){
        Sede sede = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a modificar no encontrada"));

        sede.setNumberSedeTelefono(s.getNumberSedeTelefono());
        repo.save(sede);
        return "Numero modificado";
    }

    public String deleteSedeId(Long id){
        Sede sede = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Sede no encontrado"));
        repo.delete(sede);
        return "Sede eliminada";
    }
}
