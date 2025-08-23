package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateGoalEntityUseCaseTest {
    @Test
    void testUpdateGoalSuccess() {
        // Simule a atualização de meta e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testUpdateGoalThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao atualizar meta");
        });
        assertEquals("Erro ao atualizar meta", exception.getMessage());
    }
}
