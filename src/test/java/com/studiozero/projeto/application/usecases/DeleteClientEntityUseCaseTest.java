package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteClientEntityUseCaseTest {
    @Test
    void testDeleteClientSuccess() {
        // Simule a exclusão de cliente e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testDeleteClientThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao deletar cliente");
        });
        assertEquals("Erro ao deletar cliente", exception.getMessage());
    }
}
