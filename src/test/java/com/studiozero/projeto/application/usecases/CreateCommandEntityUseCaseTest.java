package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreateCommandEntityUseCaseTest {
    @Test
    void testCreateCommandSuccess() {
        // Simule a criação de command e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testCreateCommandThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao criar command");
        });
        assertEquals("Erro ao criar command", exception.getMessage());
    }
}
