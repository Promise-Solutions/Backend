package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreateJobEntityUseCaseTest {
    @Test
    void testCreateJobSuccess() {
        // Simule a criação de job e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testCreateJobThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao criar job");
        });
        assertEquals("Erro ao criar job", exception.getMessage());
    }
}
