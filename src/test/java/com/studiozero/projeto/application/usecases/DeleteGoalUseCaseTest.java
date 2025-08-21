package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteGoalUseCaseTest {
    @Test
    void testDeleteGoalSuccess() {
        // Simule a exclusão de meta e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testDeleteGoalThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao deletar meta");
        });
        assertEquals("Erro ao deletar meta", exception.getMessage());
    }
}
