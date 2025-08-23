package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Goal;
import com.studiozero.projeto.domain.repositories.GoalRepository;
import com.studiozero.projeto.infrastructure.entities.GoalEntity;
import com.studiozero.projeto.infrastructure.mappers.GoalEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaGoalRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class GoalRepositoryImpl implements GoalRepository {
    private final JpaGoalRepository jpaGoalRepository;

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
