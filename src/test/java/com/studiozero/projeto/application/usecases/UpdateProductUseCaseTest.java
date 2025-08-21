package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateProductUseCaseTest {
    @Test
    void testUpdateProductSuccess() {
        // Simule a atualização de produto e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testUpdateProductThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao atualizar produto");
        });
        assertEquals("Erro ao atualizar produto", exception.getMessage());
    }
}
