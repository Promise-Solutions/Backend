package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Integer> {
    Optional<Goal> findTopByOrderByIdDesc();

}
