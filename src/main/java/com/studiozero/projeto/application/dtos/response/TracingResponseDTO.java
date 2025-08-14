package com.studiozero.projeto.application.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.Tracing;
import com.studiozero.projeto.application.enums.Context;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class TracingResponseDTO {
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Context context;

    private LocalDateTime dateTime;

    public TracingResponseDTO(Tracing tracing) {
        this.id = id;
        this.context = context;
        this.dateTime= dateTime;
    }
}
