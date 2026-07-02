package com.TiendaDisco.AdministracionEnvios.exception;


/**
 * Excepcion personalizada para errores de negocio en envios.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public class ManejoErrores extends RuntimeException {

    public ManejoErrores (String msje){
        super(msje);
    }

}
