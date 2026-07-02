package com.TiendaDisco.AdministracionUsuario.exception;

/**
 * Excepcion personalizada para errores de negocio en el modulo de usuarios.
 * <p>Se lanza cuando una operacion no puede completarse por una violacion
 * de las reglas de negocio (ej. usuario no encontrado).</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public class ManejoErrores extends RuntimeException{

    public ManejoErrores (String mensaje){
        super(mensaje);
    }

}
