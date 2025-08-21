package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Goal;
import com.studiozero.projeto.domain.repositories.GoalRepository;
import com.studiozero.projeto.infrastructure.repositories.JpaGoalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoalRepositoryImpl implements GoalRepository {
    private final JpaGoalRepository jpaGoalRepository;

    @Autowired
    public GoalRepositoryImpl(JpaGoalRepository jpaGoalRepository) {
        this.jpaGoalRepository = jpaGoalRepository;
    }

    @Override
    public Goal findById(Integer id) {
        return jpaGoalRepository.findById(id).orElse(null);
    }

    @Override
    public java.util.Optional<Goal> findTopByOrderByIdDesc() {
        return jpaGoalRepository.findAll().stream()
                .max((g1, g2) -> Integer.compare(g1.getId(), g2.getId()));
    }

    @Override
    public void save(Goal goal) {
        jpaGoalRepository.save(goal);
    }

    @Override
    public void deleteById(Integer id) {
        jpaGoalRepository.deleteById(id);
    }

    @Override
    public List<Goal> listAll() {
        return jpaGoalRepository.findAll();
    }

    @Override
    public List<Goal> findAll() {
        return jpaGoalRepository.findAll();
    }
}
