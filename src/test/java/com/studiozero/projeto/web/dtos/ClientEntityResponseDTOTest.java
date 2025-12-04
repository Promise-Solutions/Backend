package com.studiozero.projeto.web.dtos;

import com.studiozero.projeto.application.enums.ClientType;
import com.studiozero.projeto.web.dtos.response.ClientResponseDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ClientEntityResponseDTOTest {
    @Test
    void testDTOCreation() {
        UUID id = UUID.randomUUID();
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setId(id);
        dto.setName("Alice");
        dto.setCpf("123.456.789-00");
        dto.setEmail("alice@example.com");
        dto.setContact("87654321");
        dto.setClientType(ClientType.SINGLE);
        dto.setActive(true);
        dto.setBirthDay(LocalDate.of(1990, 1, 1));
        dto.setCreatedDate(LocalDate.now());

        assertEquals(id, dto.getId());
        assertEquals("Alice", dto.getName());
        assertEquals("123.456.789-00", dto.getCpf());
        assertEquals("alice@example.com", dto.getEmail());
        assertEquals("87654321", dto.getContact());
        assertEquals(ClientType.SINGLE, dto.getClientType());
        assertTrue(dto.getActive());
        assertEquals(LocalDate.of(1990, 1, 1), dto.getBirthDay());
        assertNotNull(dto.getCreatedDate());
    }

    @Test
    void testDTOInvalidData() {
        ClientResponseDTO dto = new ClientResponseDTO();
        assertNull(dto.getId());
        assertNull(dto.getName());
        // setting invalid email should still be allowed at DTO level (no validation)
        dto.setEmail("not-an-email");
        assertEquals("not-an-email", dto.getEmail());
    }
}
