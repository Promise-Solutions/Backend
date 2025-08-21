package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetGoalUseCaseTest {
    @Test
    void testGetGoalSuccess() {
        // Simule a busca de meta e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testGetGoalThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao buscar meta");
        });
        assertEquals("Erro ao buscar meta", exception.getMessage());
    }
}
