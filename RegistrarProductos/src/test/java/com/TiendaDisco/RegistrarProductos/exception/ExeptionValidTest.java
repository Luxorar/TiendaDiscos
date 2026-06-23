package com.TiendaDisco.RegistrarProductos.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExeptionValidTest {

    private final ExeptionValid controllerAdvice = new ExeptionValid();

    @Test
    void handleManejoErrores_WithException_ShouldReturnNotFound() {
        ManejoErrores ex = new ManejoErrores("Recurso no encontrado");

        ResponseEntity<Map<String, String>> response = controllerAdvice.handleManejoErrores(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Recurso no encontrado", response.getBody().get("error"));
    }
}
