package com.TiendaDisco.RegistrarDiscos.service;

import java.util.List;
import com.TiendaDisco.RegistrarDiscos.dto.DiscoDTO;
import com.TiendaDisco.RegistrarDiscos.exception.ManejoErrores;
import com.TiendaDisco.RegistrarDiscos.mapper.Mapper;
import com.TiendaDisco.RegistrarDiscos.model.Disco;
import com.TiendaDisco.RegistrarDiscos.repository.DiscoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscoService implements IDiscoService {

    @Autowired
    private DiscoRepository discoRepository;

    @Override
    public Disco postDisco(Disco d) {
        return discoRepository.save(d);
    }

    @Override
    public DiscoDTO getDiscoId(Long id) {
        Disco disco = discoRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrado"));
        return Mapper.toDTO(disco);
    }

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

    @Override
    public String deleteDisco(Long id) {
        Disco disc = discoRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a eliminar no encontrado"));

        discoRepository.delete(disc);
        return "Disco eliminado";
    }

    @Override
    public List<DiscoDTO> getAllDiscos() {
        return discoRepository.findAll().stream()
                .map(Mapper::toDTO)
                .toList();
    }
}
