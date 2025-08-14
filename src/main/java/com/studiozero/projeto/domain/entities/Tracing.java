package com.studiozero.projeto.domain.entities;

import com.studiozero.projeto.application.enums.Context;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "rastreio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tracing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rastreio", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "contexto", nullable = false)
    @Enumerated(EnumType.STRING)
    private Context context;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dateTime;
}
