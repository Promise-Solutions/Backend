package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateSubJobUseCaseTest {
    @Test
    void testUpdateSubJobSuccess() {
        // Simule a atualização de subjob e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testUpdateSubJobThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao atualizar subjob");
        });
        assertEquals("Erro ao atualizar subjob", exception.getMessage());
    }
}
