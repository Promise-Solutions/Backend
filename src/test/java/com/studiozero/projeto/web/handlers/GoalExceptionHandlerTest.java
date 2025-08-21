package com.studiozero.projeto.web.handlers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoalExceptionHandlerTest {
    @Test
    void testHandleNotFoundException() {
        RuntimeException ex = new RuntimeException("Meta não encontrada");
        assertEquals("Meta não encontrada", ex.getMessage());
    }

    @Test
    void testHandleBadRequestException() {
        RuntimeException ex = new RuntimeException("Requisição inválida");
        assertEquals("Requisição inválida", ex.getMessage());
    }
}
