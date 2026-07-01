package com.TiendaDisco.RegistrarSede.service;

import com.TiendaDisco.RegistrarSede.dto.SedeDTO;
import com.TiendaDisco.RegistrarSede.exception.ManejoErrores;
import com.TiendaDisco.RegistrarSede.mapper.Mapper;
import com.TiendaDisco.RegistrarSede.model.Sede;
import com.TiendaDisco.RegistrarSede.repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de gestionar la logica de negocio para las sedes.
 * Contiene las operaciones para registrar, validar y consultar las sedes
 * * @author Diego Barria
 * * @author Fernando Castillo
 * * @author Luis Villalon
 * @version 1.0.0
 */
@Service
public class SedeService implements ISedeService{
    @Autowired
    private SedeRepository repo;

    /**
     * Registra y guarda una nueva sede en la base de datos.
     * * @param d El objeto {@link Sede} con la informacion a registrar.
     * @return La sede es guardado junto con el ID autogenerado por la base de datos.
     */
    public Sede postSede(Sede s){
        return repo.save(s);
    }

    /**
     * Busca y obtiene la informacion de una sede en base a su id.
     * La respuesta es transformada a un DTO
     * * @param id el id de la sede que se desea buscar
     * @return Un objeto {@link SedeDTO} con la informacion de la sede solicitada.
     * @throws ManejoErrores Si no existe ninguna sede con el ID proporcionado.
     */
    public SedeDTO getSedeId(Long id){
        Sede sede = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrado"));
        return Mapper.toDTO(sede);
    }

    /**
     * Recupera una lista con todoas las sedes registradas actualmente en el sistema.
     * Cada sede encontrado es mapeado a su DTO
     * @return Una coleccion {@link List} de {@link SedeDTO}.
     */
    public List<SedeDTO> getAllSedes(){
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    /**
     * Modifica los datos de una sede existente en el sistema.
     * Actualiza el numero telefonico
     * * @param id El identificador unico de la sede que se va a modificar.
     * @param s El objeto {@link Sede} con los nuevos datos a actualizar.
     * @return Un mensaje de tipo String confirmando que se modifico la sede.
     * @throws ManejoErrores Si el ID a modificar no es encontrado en la base de datos
     */
    public String putSede(Long id, Sede s){
        Sede sede = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a modificar no encontrada"));

        sede.setNumberSedeTelefono(s.getNumberSedeTelefono());
        repo.save(sede);
        return "Numero modificado";
    }

    /**
     * Elimina permanentemente una sede en la base de datos mediante su ID.
     * * @param id El identificador unico de la sede que se desea eliminar.
     * @return Un mensaje de tipo String confirmando que se elimino la sede.
     * @throws ManejoErrores Si el id a eliminar no existe en la base de datos.
     */
    public String deleteSedeId(Long id){
        Sede sede = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Sede no encontrado"));
        repo.delete(sede);
        return "Sede eliminada";
    }
}
