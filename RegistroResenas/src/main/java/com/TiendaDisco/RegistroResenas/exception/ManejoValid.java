package com.TiendaDisco.RegistroResenas.exception;

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
 * Interceptor global de excepciones para los controladores REST.
 * Captura las excepciones lanzadas durante la ejecución de las peticiones HTTP y
 * las formatea en respuestas JSON estandarizadas (Map) para el cliente,
 * además de registrar los incidentes en el log del sistema.
 * * @author Luxorar
 * @version 1.0.0
 */
@RestControllerAdvice
public class ManejoValid {

    private static final Logger logger = LoggerFactory.getLogger(ManejoValid.class);

    /**
     * Intercepta errores de validación de datos (anotaciones como @Valid, @NotNull, etc.).
     * Extrae cada campo que falló junto con su mensaje de error específico.
     * * @param ex La excepción lanzada cuando los argumentos del método no son válidos.
     * @return Una respuesta HTTP 400 (Bad Request) con un mapa de los campos inválidos y sus motivos.
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
     * Intercepta las excepciones personalizadas de lógica de negocio (por ejemplo, recurso no encontrado).
     * * @param ex La excepción de negocio lanzada manualmente en los servicios.
     * @return Una respuesta HTTP 404 (Not Found) con el mensaje exacto del error.
     */
    @ExceptionHandler(ManejoErrores.class)
    public ResponseEntity<Map<String, String>> handleManejoErrores(ManejoErrores ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        logger.info("Error de negocio: {}", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Red de seguridad (Catch-all) para cualquier excepción no controlada en el servidor.
     * Oculta los detalles técnicos (stack trace) al cliente por seguridad, devolviendo
     * un mensaje genérico, pero registra el error completo en los logs internos.
     * * @param ex La excepción genérica o inesperada lanzada por la aplicación.
     * @return Una respuesta HTTP 500 (Internal Server Error) con un mensaje amigable.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> error = new HashMap<>();

        error.put("error", "Ocurrió un error interno en el servidor. Por favor, intente más tarde.");

        logger.error("Error interno del servidor no controlado: ", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}