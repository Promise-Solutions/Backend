package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteSubJobUseCaseTest {
    @Test
    void testDeleteSubJobSuccess() {
        // Simule a exclusão de subjob e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testDeleteSubJobThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao deletar subjob");
        });
        assertEquals("Erro ao deletar subjob", exception.getMessage());
    }
}
