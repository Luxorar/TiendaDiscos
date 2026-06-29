package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.DTO.ResenaDTO;
import com.TiendaDisco.RegistroResenas.exception.ManejoErrores;
import com.TiendaDisco.RegistroResenas.mapper.Mapper;
import com.TiendaDisco.RegistroResenas.model.Resena;
import com.TiendaDisco.RegistroResenas.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService implements IResenaService {
    @Autowired
    private ResenaRepository repo;

    //==================REGISTRA UNA RESENA================================
    public Resena postResena(Resena r){
        return repo.save(r);
    }

    //==================OBTIENE TODAS LAS RESENAS================================
    public List<ResenaDTO> getAllResenas(){
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    //==================OBTIENE RESENA POR ID================================
    public ResenaDTO getResenaId(Long id){
        Resena resena = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrada"));
        return Mapper.toDTO(resena);
    }

    //==================ELIMINA UNA RESENA================================
    public String deleteResena(Long id){
        Resena resena = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Reseña no encontrada"));
        repo.delete(resena);
        return "Reseña eliminada";
    }
}
