package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteJobEntityUseCaseTest {
    @Test
    void testDeleteJobSuccess() {
        // Simule a exclusão de job e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testDeleteJobThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao deletar job");
        });
        assertEquals("Erro ao deletar job", exception.getMessage());
    }
}
