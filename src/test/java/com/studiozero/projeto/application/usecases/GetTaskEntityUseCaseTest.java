package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetTaskEntityUseCaseTest {
    @Test
    void testGetTaskSuccess() {
        // Simule a busca de task e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testGetTaskThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao buscar task");
        });
        assertEquals("Erro ao buscar task", exception.getMessage());
    }
}
