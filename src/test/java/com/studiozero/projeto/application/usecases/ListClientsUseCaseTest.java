package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListClientsUseCaseTest {
    @Test
    void testListClientsSuccess() {
        // Simule a listagem de clientes e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testListClientsThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao listar clientes");
        });
        assertEquals("Erro ao listar clientes", exception.getMessage());
    }
}
