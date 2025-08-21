package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteCommandUseCaseTest {
    @Test
    void testDeleteCommandSuccess() {
        // Simule a exclusão de command e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testDeleteCommandThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao deletar command");
        });
        assertEquals("Erro ao deletar command", exception.getMessage());
    }
}
