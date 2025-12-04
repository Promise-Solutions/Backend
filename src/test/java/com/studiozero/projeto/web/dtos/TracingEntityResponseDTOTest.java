package com.studiozero.projeto.web.dtos;

import com.studiozero.projeto.web.dtos.response.TracingResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TracingEntityResponseDTOTest {
    @Test
    void testDtoPojo() {
        TracingResponseDTO dto = new TracingResponseDTO();
        assertNotNull(dto);
        // default fields are null
        assertNull(dto.getId());
    }
}
