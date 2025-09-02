package com.studiozero.projeto.web.handlers;

import com.studiozero.projeto.shared.web.handlers.BadRequestException;
import com.studiozero.projeto.shared.web.handlers.NotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionHandlerClassTest {
    @Test
    void testHandleNotFoundException() {
        NotFoundException ex = new NotFoundException("Não encontrado");
        assertEquals("Não encontrado", ex.getMessage());
    }

    @Test
    void testHandleBadRequestException() {
        BadRequestException ex = new BadRequestException("Requisição inválida");
        assertEquals("Requisição inválida", ex.getMessage());
    }
}
