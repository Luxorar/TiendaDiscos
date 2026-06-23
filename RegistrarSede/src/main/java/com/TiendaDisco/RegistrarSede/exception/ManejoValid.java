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

@RestControllerAdvice
public class ManejoValid {

    private static final Logger logger = LoggerFactory.getLogger(ManejoValid.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejo(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((FieldError err) ->
                errores.put(err.getField(), err.getDefaultMessage())
        );

        logger.warn("Peticion rechazada por validacion: {}", errores);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }

    @ExceptionHandler(ManejoErrores.class)
    public ResponseEntity<Map<String, String>> handleManejoErrores(ManejoErrores ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        logger.info("Error de negocio: {}", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> error = new HashMap<>();

        error.put("error", "Ocurrió un error interno en el servidor. Por favor, intente más tarde.");

        logger.error("Error interno del servidor no controlado: ", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}