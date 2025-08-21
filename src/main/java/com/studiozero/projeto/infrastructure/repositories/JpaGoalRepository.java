package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaGoalRepository extends JpaRepository<Goal, Integer> {
}
