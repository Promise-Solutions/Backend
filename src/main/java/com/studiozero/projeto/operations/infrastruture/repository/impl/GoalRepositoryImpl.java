package com.studiozero.projeto.operations.infrastruture.repository.impl;

import com.studiozero.projeto.operations.domain.entities.Goal;
import com.studiozero.projeto.operations.domain.repositories.GoalRepository;
import com.studiozero.projeto.operations.infrastruture.entity.GoalEntity;
import com.studiozero.projeto.operations.infrastruture.mapper.GoalEntityMapper;
import com.studiozero.projeto.operations.infrastruture.repository.jpa.JpaGoalRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GoalRepositoryImpl implements GoalRepository {
    private final JpaGoalRepository jpaGoalRepository;

    public GoalRepositoryImpl(JpaGoalRepository jpaGoalRepository) {
        this.jpaGoalRepository = jpaGoalRepository;
    }

    @Override
    public Goal findById(Integer id) {
        return jpaGoalRepository.findById(id)
            .map(GoalEntityMapper::toDomain)
            .orElse(null);
    }

    @Override
    public Optional<Goal> findTopByOrderByIdDesc() {
        return jpaGoalRepository.findAll().stream()
            .map(GoalEntityMapper::toDomain)
            .max((g1, g2) -> Integer.compare(g1.getId(), g2.getId()));
    }

    @Override
    public void save(Goal goal) {
        GoalEntity entity = GoalEntityMapper.toEntity(goal);
        jpaGoalRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        jpaGoalRepository.deleteById(id);
    }


    @Override
    public List<Goal> findAll() {
        return jpaGoalRepository.findAll().stream()
            .map(GoalEntityMapper::toDomain)
            .toList();
    }
}
