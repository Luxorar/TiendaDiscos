package com.TiendaDisco.RegistrarDiscos.exception;

/**
 * Excepción personalizada de tiempo de ejecución (Runtime) utilizada para señalar
 * errores de lógica de negocio o recursos no encontrados en la tienda de discos.
 * Por ejemplo: cuando se solicita un ID de disco que no existe.
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public class ManejoErrores extends RuntimeException {

    /**
     * Construye una nueva excepción con un mensaje detallado sobre el fallo específico.
     * * @param mensaje El mensaje descriptivo del error que será enviado al cliente.
     */
    public ManejoErrores(String msje) {
        super(msje);
    }
}