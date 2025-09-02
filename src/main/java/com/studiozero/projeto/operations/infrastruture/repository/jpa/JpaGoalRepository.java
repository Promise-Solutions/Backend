package com.studiozero.projeto.operations.infrastruture.repository.jpa;

import com.studiozero.projeto.operations.infrastruture.entity.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaGoalRepository extends JpaRepository<GoalEntity, Integer> {
}
