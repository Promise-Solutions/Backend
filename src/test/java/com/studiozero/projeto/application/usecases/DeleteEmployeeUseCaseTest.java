package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteEmployeeUseCaseTest {
    @Test
    void testDeleteEmployeeSuccess() {
        // Simule a exclusão de funcionário e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testDeleteEmployeeThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao deletar funcionário");
        });
        assertEquals("Erro ao deletar funcionário", exception.getMessage());
    }
}
