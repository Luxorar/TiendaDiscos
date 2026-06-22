package com.TiendaDisco.AdministracionUsuario.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExceptionValidTest {

    private final ExceptionValid controllerAdvice = new ExceptionValid();

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private WebRequest webRequest;
    @Mock
    private ConstraintViolationException constraintViolationException;
    @Mock
    private ConstraintViolation<?> constraintViolation;
    @Mock
    private Path propertyPath;

    @Test
    void handleMethodArgumentNotValid_WithFieldErrors_ShouldReturnBadRequest() {
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(
                new FieldError("obj", "field1", "Error message 1"),
                new FieldError("obj", "field2", "Error message 2")
        ));

        ResponseEntity<Object> response = controllerAdvice.handleMethodArgumentNotValid(
                methodArgumentNotValidException, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertInstanceOf(Map.class, response.getBody());
        Map<String, String> errors = (Map<String, String>) response.getBody();
        assertEquals("Error message 1", errors.get("field1"));
        assertEquals("Error message 2", errors.get("field2"));
    }

    @Test
    void handleConstraintViolation_WithViolations_ShouldReturnBadRequest() {
        when(constraintViolationException.getConstraintViolations()).thenReturn(Set.of(constraintViolation));
        when(constraintViolation.getPropertyPath()).thenReturn(propertyPath);
        when(propertyPath.toString()).thenReturn("field1");
        when(constraintViolation.getMessage()).thenReturn("Constraint violation message");

        ResponseEntity<Map<String, String>> response = controllerAdvice.handleConstraintViolation(constraintViolationException);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Constraint violation message", response.getBody().get("field1"));
    }

    @Test
    void handleManejoErrores_WithException_ShouldReturnNotFound() {
        ManejoErrores ex = new ManejoErrores("Recurso no encontrado");

        ResponseEntity<Map<String, String>> response = controllerAdvice.handleManejoErrores(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Recurso no encontrado", response.getBody().get("error"));
    }
}
