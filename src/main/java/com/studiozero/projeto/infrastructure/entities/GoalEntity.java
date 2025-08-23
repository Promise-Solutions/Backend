package com.studiozero.projeto.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_meta", nullable = false)
    private Integer id;

    @Column(name = "meta", nullable = false)
    private Double goal;

    public void setName(String increaseRevenue) {
    }

    public void setTargetValue(double v) {
    }
}
