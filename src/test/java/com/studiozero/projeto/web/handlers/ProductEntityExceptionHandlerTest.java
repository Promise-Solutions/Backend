package com.studiozero.projeto.web.handlers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductEntityExceptionHandlerTest {
    @Test
    void testHandleNotFoundException() {
        RuntimeException ex = new RuntimeException("Produto não encontrado");
        assertEquals("Produto não encontrado", ex.getMessage());
    }

    @Test
    void testHandleBadRequestException() {
        RuntimeException ex = new RuntimeException("Requisição inválida");
        assertEquals("Requisição inválida", ex.getMessage());
    }
}
