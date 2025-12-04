package com.studiozero.projeto.web.dtos;

import com.studiozero.projeto.web.dtos.response.TaskResponseDTO;
import com.studiozero.projeto.application.enums.Status;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TaskEntityResponseDTOTest {
    @Test
    void testDtoPojo() {
        UUID id = UUID.randomUUID();
        UUID fkEmp = UUID.randomUUID();
        UUID fkAssigned = UUID.randomUUID();
        LocalDate start = LocalDate.of(2025, 1, 1);
        LocalDate limit = LocalDate.of(2025, 1, 31);

        TaskResponseDTO dto = new TaskResponseDTO(id, "Title", "Desc", start, limit, fkEmp, fkAssigned, Status.OPEN);

        assertNotNull(dto);
        assertEquals(id, dto.getId());
        assertEquals("Title", dto.getTitle());
        assertEquals("Desc", dto.getDescription());
        assertEquals(start, dto.getStartDate());
        assertEquals(limit, dto.getLimitDate());
        assertEquals(fkEmp, dto.getFkEmployee());
        assertEquals(fkAssigned, dto.getFkAssigned());
        assertEquals(Status.OPEN, dto.getStatus());

        // exercise setters
        dto.setTitle("New Title");
        assertEquals("New Title", dto.getTitle());
    }

    @Test
    void testDTOInvalidData() {
        TaskResponseDTO dto = new TaskResponseDTO();
        assertNull(dto.getId());
        // DTO accepts arbitrary values; ensure setters/getters work
        dto.setDescription("x");
        assertEquals("x", dto.getDescription());
    }
}
