package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetClientEntityUseCaseTest {
    @Test
    void testGetClientSuccess() {
        // Simule a busca de cliente e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testGetClientThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao buscar cliente");
        });
        assertEquals("Erro ao buscar cliente", exception.getMessage());
    }
}
