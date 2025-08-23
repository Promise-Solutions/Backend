package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.infrastructure.entities.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaGoalRepository extends JpaRepository<GoalEntity, Integer> {
}
