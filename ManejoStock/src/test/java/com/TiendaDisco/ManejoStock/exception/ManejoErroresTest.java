package com.TiendaDisco.ManejoStock.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ManejoErroresTest {

    @Test
    void constructor_WithMessage_ShouldSetMessage() {
        ManejoErrores exception = new ManejoErrores("Test error");
        assertEquals("Test error", exception.getMessage());
    }

    @Test
    void constructor_ShouldExtendRuntimeException() {
        ManejoErrores exception = new ManejoErrores("test");
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void throwManejoErrores_ShouldBeCaughtByAssertThrows() {
        ManejoErrores exception = assertThrows(ManejoErrores.class, () -> {
            throw new ManejoErrores("Not found");
        });
        assertEquals("Not found", exception.getMessage());
    }
}
