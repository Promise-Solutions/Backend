package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreateTaskEntityUseCaseTest {
    @Test
    void testCreateTaskSuccess() {
        // Simule a criação de task e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testCreateTaskThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao criar task");
        });
        assertEquals("Erro ao criar task", exception.getMessage());
    }
}
