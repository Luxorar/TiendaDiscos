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

/**
 * Servicio encargado de gestionar la logica de negocio para los discos.
 * Contiene las operaciones para registrar, validar y consultar el catalogo de discos
 * * @author Diego Barria
 * * @author Fernando Castillo
 * * @author Luis Villalon
 * @version 1.0.0
 */
@Service
public class DiscoService implements IDiscoService {

    @Autowired
    private DiscoRepository discoRepository;

    /**
     * Registra y guarda un nuevo disco en la base de datos.
     * * @param d El objeto {@link Disco} con la informacion a registrar.
     * @return El disco guardado junto con el ID autogenerado por la base de datos.
     */
    @Override
    public Disco postDisco(Disco d) {
        return discoRepository.save(d);
    }

    /**
     * Busca y obtiene la informacion de un disco en base a su id.
     * La respuesta es transformada a un DTO
     * * @param id el id del disco que se desea buscar
     * @return Un objeto {@link DiscoDTO} con la informacion del disco solicitado.
     * @throws ManejoErrores Si no existe ningun disco con el ID proporcionado.
     */
    @Override
    @Transactional(readOnly = true)
    public DiscoDTO getDiscoId(Long id) {
        Disco disco = discoRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrado"));
        return Mapper.toDTO(disco);
    }

    /**
     * Modifica los datos de un disco existente en el sistema.
     * Actualiza el nombre del disco, el artista y el precio
     * * @param id El identificador unico del disco que se va a modificar.
     * @param d El objeto {@link Disco} con los nuevos datos a actualizar.
     * @return Un mensaje de tipo String confirmando que se modifico el disco.
     * @throws ManejoErrores Si el ID a modificar no es encontrado en la base de datos
     */
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

    /**
     * Elimina permanentemente un disco en la base de datos mediante su ID.
     * * @param id El identificador unico del disco que se desea eliminar.
     * @return Un mensaje de tipo String confirmando que se elimino el disco.
     * @throws ManejoErrores Si el id a eliminar no existe en la base de datos.
     */
    @Override
    public String deleteDisco(Long id) {
        Disco disc = discoRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a eliminar no encontrado"));

        discoRepository.delete(disc);
        return "Disco eliminado";
    }

    /**
     * Recupera una lista con todos los discos registrados actualmente en el sistema.
     * Cada disco encontrado es mapeado a su DTO
     * @return Una coleccion {@link List} de objetos {@link DiscoDTO} con el catalogo completo.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DiscoDTO> getAllDiscos() {
        return discoRepository.findAll().stream()
                .map(Mapper::toDTO)
                .toList();
    }
}
