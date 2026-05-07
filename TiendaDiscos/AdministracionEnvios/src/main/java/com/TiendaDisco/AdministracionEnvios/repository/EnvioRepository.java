package com.TiendaDisco.AdministracionEnvios.repository;

import com.TiendaDisco.AdministracionEnvios.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface EnvioRepository extends JpaRepository<Envio, Long> {

    ArrayList<Envio> listaEnvios = new ArrayList<>();

    default Envio postEnvio(Envio envio) {
        listaEnvios.add(envio);
        return envio;
    }

    default ArrayList<Envio> getAllEnvios(){
        return listaEnvios;
    }

    default Envio getEnvioId(Long id){
        for (Envio envio : listaEnvios) {
            if(envio.getId().equals(id)){
                return envio;
            }
        }
        return null;
    }

    default Envio PutEstadoEnvio(Envio envio, Long id){
        for (Envio envio2 : listaEnvios) {
            if(envio2.getId().equals(id)){
                envio2.setEstadoEnvio(envio.getEstadoEnvio());

                return envio2;
            }
        }
        return null;
    }

    default Envio PutDirEnvio(Envio envio, Long id){
        for (Envio envio2 : listaEnvios) {
            if(envio2.getId().equals(id)){
                envio2.setDireccionDestino(envio.getDireccionDestino());

                return envio2;
            }
        }
        return null;
    }

    default Envio deleteEnvio(Long id){
        for (Envio envio : listaEnvios) {
            if(envio.getId().equals(id)){
                listaEnvios.remove(envio);
                return envio;
            }
        }
        return null;
    }
}
