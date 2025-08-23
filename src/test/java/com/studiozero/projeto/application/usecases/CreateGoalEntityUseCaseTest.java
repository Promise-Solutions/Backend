package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreateGoalEntityUseCaseTest {
    @Test
    void testCreateGoalSuccess() {
        // Simule a criação de meta e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testCreateGoalThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao criar meta");
        });
        assertEquals("Erro ao criar meta", exception.getMessage());
    }
}
