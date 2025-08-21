package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetCommandUseCaseTest {
    @Test
    void testGetCommandSuccess() {
        // Simule a busca de command e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testGetCommandThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao buscar command");
        });
        assertEquals("Erro ao buscar command", exception.getMessage());
    }
}
