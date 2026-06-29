package com.TiendaDisco.RegistrarDiscos.service;

import java.util.List;
import com.TiendaDisco.RegistrarDiscos.dto.DiscoDTO;
import com.TiendaDisco.RegistrarDiscos.exception.ManejoErrores;
import com.TiendaDisco.RegistrarDiscos.mapper.Mapper;
import com.TiendaDisco.RegistrarDiscos.model.Disco;
import com.TiendaDisco.RegistrarDiscos.repository.DiscoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscoService implements IDiscoService {

    @Autowired
    private DiscoRepository discoRepository;

    //==================REGISTRA UN DISCO================================
    @Override
    public Disco postDisco(Disco d) {
        return discoRepository.save(d);
    }

    //==================OBTIENE DISCO POR ID================================
    @Override
    @Transactional(readOnly = true)
    public DiscoDTO getDiscoId(Long id) {
        Disco disco = discoRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrado"));
        return Mapper.toDTO(disco);
    }

    //==================MODIFICA UN DISCO================================
    @Override
    public String putDisco(Long id, Disco d) {
        Disco disc = discoRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a modificar no encontrado"));

        disc.setNombreDisco(d.getNombreDisco());
        disc.setArtista(d.getArtista());
        disc.setPrecio(d.getPrecio());

        discoRepository.save(disc);
        return "Disco modificado";
    }

    //==================ELIMINA UN DISCO================================
    @Override
    public String deleteDisco(Long id) {
        Disco disc = discoRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a eliminar no encontrado"));

        discoRepository.delete(disc);
        return "Disco eliminado";
    }

    //==================OBTIENE TODOS LOS DISCOS================================
    @Override
    @Transactional(readOnly = true)
    public List<DiscoDTO> getAllDiscos() {
        return discoRepository.findAll().stream()
                .map(Mapper::toDTO)
                .toList();
    }
}
