package com.TiendaDisco.RegistroResenas.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManejoValidTest {

    private final ManejoValid controllerAdvice = new ManejoValid();

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;
    @Mock
    private BindingResult bindingResult;

    @Test
    void manejo_WithFieldErrors_ShouldReturnBadRequest() {
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(
                new FieldError("obj", "field1", "Error message 1"),
                new FieldError("obj", "field2", "Error message 2")
        ));

        ResponseEntity<Map<String, String>> response = controllerAdvice.manejo(methodArgumentNotValidException);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error message 1", response.getBody().get("field1"));
        assertEquals("Error message 2", response.getBody().get("field2"));
    }
}
