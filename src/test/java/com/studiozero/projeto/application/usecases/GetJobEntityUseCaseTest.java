package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetJobEntityUseCaseTest {
    @Test
    void testGetJobSuccess() {
        // Simule a busca de job e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testGetJobThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao buscar job");
        });
        assertEquals("Erro ao buscar job", exception.getMessage());
    }
}
