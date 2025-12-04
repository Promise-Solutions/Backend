package com.studiozero.projeto.web.dtos;

import com.studiozero.projeto.web.dtos.response.JobResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class JobEntityResponseDTOTest {
    @Test
    void testDTOCreation() {
        UUID id = UUID.randomUUID();
        UUID fkClient = UUID.randomUUID();
        JobResponseDTO dto = new JobResponseDTO(id, fkClient, "Title", 100.0, null, null, null, null);

        assertEquals(id, dto.getId());
        assertEquals(fkClient, dto.getFkClient());
        assertEquals("Title", dto.getTitle());
        assertEquals(100.0, dto.getTotalValue());
    }

    @Test
    void testDTOInvalidData() {
        JobResponseDTO dto = new JobResponseDTO();
        assertNull(dto.getId());
        dto.setTitle("t");
        assertEquals("t", dto.getTitle());
    }
}
