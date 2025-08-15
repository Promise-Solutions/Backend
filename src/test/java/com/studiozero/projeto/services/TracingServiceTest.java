package com.studiozero.projeto.services;

import static org.junit.jupiter.api.Assertions.*;

import com.studiozero.projeto.application.services.TracingService;
import com.studiozero.projeto.domain.entities.Tracing;
import com.studiozero.projeto.application.enums.Context;
import com.studiozero.projeto.infrastructure.exceptions.NotFoundException;
import com.studiozero.projeto.domain.repositories.TracingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

class TracingServiceTest {

    private TracingRepository tracingRepository;
    private TracingService tracingService;

    @BeforeEach
    void setUp() {
        tracingRepository = mock(TracingRepository.class);
        tracingService = new TracingService(tracingRepository);
    }

    @Test
    @DisplayName("Should create tracing with current date/time")
    void shouldCreateTracing() {
        Tracing tracing = new Tracing();
        Tracing savedTracing = new Tracing();
        savedTracing.setId(1);
        savedTracing.setDateTime(LocalDateTime.now());

        when(tracingRepository.save(any(Tracing.class))).thenReturn(savedTracing);

        Tracing result = tracingService.createTracing(tracing);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getDateTime());
        verify(tracingRepository).save(any(Tracing.class));
    }

    @Test
    @DisplayName("Should set context and save tracing")
    void shouldSetContextAndSaveTracing() {
        Context context = Context.EXPENSE;
        Tracing savedTracing = new Tracing();
        savedTracing.setId(1);
        savedTracing.setContext(context);
        savedTracing.setDateTime(LocalDateTime.now());

        when(tracingRepository.save(any(Tracing.class))).thenReturn(savedTracing);

        Tracing result = tracingService.setTracing(context);

        assertNotNull(result);
        assertEquals(context, result.getContext());
        assertNotNull(result.getDateTime());
        verify(tracingRepository).save(any(Tracing.class));
    }

    @Test
    @DisplayName("Should return list of all tracings")
    void shouldListAllTracings() {
        Tracing tracing = new Tracing();
        when(tracingRepository.findAll()).thenReturn(List.of(tracing));

        List<Tracing> result = tracingService.listTracings();

        assertEquals(1, result.size());
        verify(tracingRepository).findAll();
    }

    @Test
    @DisplayName("Should delete all tracings except the latest one")
    void shouldDeleteAllTracingsExceptLast() {
        Integer lastId = 1;
        Tracing lastTracing = new Tracing();
        lastTracing.setId(lastId);

        when(tracingRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(lastTracing));

        tracingService.deleteAllTracings();

        verify(tracingRepository).deleteAllByIdNot(lastId);
    }

    @Test
    @DisplayName("Should throw NotFoundException when no tracings exist")
    void shouldThrowWhenNoTracingsFound() {
        when(tracingRepository.findTopByOrderByIdDesc()).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> tracingService.deleteAllTracings());
    }
}
