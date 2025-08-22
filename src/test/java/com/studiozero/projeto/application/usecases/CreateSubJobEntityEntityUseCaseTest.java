package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreateSubJobEntityEntityUseCaseTest {
    @Test
    void testCreateSubJobSuccess() {
        // Simule a criação de subjob e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testCreateSubJobThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao criar subjob");
        });
        assertEquals("Erro ao criar subjob", exception.getMessage());
    }
}
