package com.studiozero.projeto.repositories;

import com.studiozero.projeto.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Integer> {
    Optional<Goal> findTopByOrderByIdDesc();

}
