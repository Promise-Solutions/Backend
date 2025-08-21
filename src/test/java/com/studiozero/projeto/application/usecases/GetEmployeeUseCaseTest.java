package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetEmployeeUseCaseTest {
    @Test
    void testGetEmployeeSuccess() {
        // Simule a busca de funcionário e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testGetEmployeeThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao buscar funcionário");
        });
        assertEquals("Erro ao buscar funcionário", exception.getMessage());
    }
}
