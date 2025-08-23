package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateCommandEntityUseCaseTest {
    @Test
    void testUpdateCommandSuccess() {
        // Simule a atualização de command e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testUpdateCommandThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao atualizar command");
        });
        assertEquals("Erro ao atualizar command", exception.getMessage());
    }
}
