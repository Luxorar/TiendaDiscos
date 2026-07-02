package com.TiendaDisco.RegistrarSede.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Interceptor global de excepciones para el microservicio de Registro de Discos.
 * Centraliza el manejo de errores lanzados por los controladores REST, estandarizando
 * las respuestas JSON enviadas al cliente y registrando los incidentes en el sistema de logs.
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@RestControllerAdvice
public class ManejoValid {

    private static final Logger logger = LoggerFactory.getLogger(ManejoValid.class);

    /**
     * Captura las excepciones de validación de datos (ej. campos vacíos o mal formateados).
     * Procesa los errores generados por la anotación @Valid en los controladores.
     * * @param ex La excepción que contiene los detalles de los campos inválidos.
     * @return Una respuesta HTTP 400 (Bad Request) con un mapa indicando qué campo falló y por qué.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejo(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((FieldError err) ->
                errores.put(err.getField(), err.getDefaultMessage())
        );

        logger.warn("Peticion rechazada por validacion: {}", errores);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }

    /**
     * Intercepta las excepciones de lógica de negocio específicas del catálogo de discos
     * (por ejemplo, cuando se intenta buscar o actualizar un disco que no existe).
     * * @param ex La excepción personalizada lanzada desde la capa de servicio.
     * @return Una respuesta HTTP 404 (Not Found) con el mensaje descriptivo del error.
     */
    @ExceptionHandler(ManejoErrores.class)
    public ResponseEntity<Map<String, String>> handleManejoErrores(ManejoErrores ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        logger.info("Error de negocio: {}", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Red de seguridad para cualquier excepción no controlada (ej. errores de conexión a la base de datos).
     * Registra la traza completa (stack trace) internamente para los desarrolladores,
     * pero devuelve un mensaje amigable al cliente para evitar exponer vulnerabilidades de seguridad.
     * * @param ex La excepción genérica o inesperada lanzada por el servidor.
     * @return Una respuesta HTTP 500 (Internal Server Error) genérica.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> error = new HashMap<>();

        error.put("error", "Ocurrió un error interno en el servidor. Por favor, intente más tarde.");

        logger.error("Error interno del servidor no controlado: ", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}