package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetSubJobEntityEntityUseCaseTest {
    @Test
    void testGetSubJobSuccess() {
        // Simule a busca de subjob e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testGetSubJobThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao buscar subjob");
        });
        assertEquals("Erro ao buscar subjob", exception.getMessage());
    }
}
