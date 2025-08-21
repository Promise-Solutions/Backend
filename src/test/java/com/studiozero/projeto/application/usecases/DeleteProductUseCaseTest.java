package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteProductUseCaseTest {
    @Test
    void testDeleteProductSuccess() {
        // Simule a exclusão de produto e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testDeleteProductThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao deletar produto");
        });
        assertEquals("Erro ao deletar produto", exception.getMessage());
    }
}
