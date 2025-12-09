package com.studiozero.projeto.web.dtos;

import com.studiozero.projeto.web.dtos.response.EmployeeResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeEntityResponseDTOTest {
    @Test
    void testDTOCreation() {
        UUID id = UUID.randomUUID();
        EmployeeResponseDTO dto = new EmployeeResponseDTO(id, "Name", "123.456.789-00", "a@b.com", "12345678", true);

        assertEquals(id, dto.getId());
        assertEquals("Name", dto.getName());
        assertEquals("123.456.789-00", dto.getCpf());
        assertTrue(dto.getActive());
    }

    @Test
    void testDTOInvalidData() {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        assertNull(dto.getEmail());
    }
}
