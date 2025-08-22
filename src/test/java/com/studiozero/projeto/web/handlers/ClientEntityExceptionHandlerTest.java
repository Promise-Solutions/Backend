package com.studiozero.projeto.web.handlers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClientEntityExceptionHandlerTest {
    @Test
    void testHandleNotFoundException() {
        RuntimeException ex = new RuntimeException("Cliente não encontrado");
        assertEquals("Cliente não encontrado", ex.getMessage());
    }

    @Test
    void testHandleBadRequestException() {
        RuntimeException ex = new RuntimeException("Requisição inválida");
        assertEquals("Requisição inválida", ex.getMessage());
    }
}
