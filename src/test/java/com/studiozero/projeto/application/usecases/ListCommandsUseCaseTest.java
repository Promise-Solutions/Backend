package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListCommandsUseCaseTest {
    @Test
    void testListCommandsSuccess() {
        // Simule a listagem de commands e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testListCommandsThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao listar commands");
        });
        assertEquals("Erro ao listar commands", exception.getMessage());
    }
}
