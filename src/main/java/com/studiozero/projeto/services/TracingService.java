package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.Tracing;
import com.studiozero.projeto.enums.Context;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.repositories.TracingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TracingService {
    private final TracingRepository tracingRepository;

    public Tracing createTracing(Tracing tracing) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", new Locale("pt", "BR"));
        tracing.setDateTime(zonedDateTime.toLocalDateTime());

        return tracingRepository.save(tracing);
    }

    public Tracing setTracing(Context context) {
        Tracing tracing = new Tracing();
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", new Locale("pt", "BR"));
        tracing.setContext(context);
        tracing.setDateTime(zonedDateTime.toLocalDateTime());

        return tracingRepository.save(tracing);
    }

    public List<Tracing> listTracings() {
        return tracingRepository.findAll();
    }

    public void deleteAllTracings() {
        List<Tracing> tracings = tracingRepository.findAll();

        if (tracings.isEmpty()) {
            throw new NotFoundException("Tracings not found");
        } else {
            tracingRepository.deleteAll();
        }
    }
}
