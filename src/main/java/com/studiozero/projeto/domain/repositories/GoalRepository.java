package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.Goal;

import java.util.List;
import java.util.Optional;

public interface GoalRepository {
    Goal findById(Integer id);

    Optional<Goal> findTopByOrderByIdDesc();

    void save(Goal goal);

    void deleteById(Integer id);

    List<Goal> listAll();

    List<Goal> findAll();
}
