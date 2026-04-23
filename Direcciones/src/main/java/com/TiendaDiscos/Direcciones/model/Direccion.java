package com.TiendaDiscos.Direcciones.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class Direccion {
    private Long id;
    @NotBlank(message = "Se necesita ingresar un pais") private String pais;
    @NotBlank(message = "Se necesita ingresar una ciudad") private String ciudad;
    @NotBlank(message = "Se necesita ingresar una direecion") private String direccion;

    public Direccion(Long id, String pais, String ciudad, String direccion) {
        this.id = id;
        this.pais = pais;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
