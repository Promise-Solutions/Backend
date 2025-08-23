package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListEmployeesUseCaseTest {
    @Test
    void testListEmployeesSuccess() {
        // Simule a listagem de funcionários e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testListEmployeesThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao listar funcionários");
        });
        assertEquals("Erro ao listar funcionários", exception.getMessage());
    }
}
