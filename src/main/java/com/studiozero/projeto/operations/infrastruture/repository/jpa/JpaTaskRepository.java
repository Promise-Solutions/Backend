package com.studiozero.projeto.operations.infrastruture.repository.jpa;

import com.studiozero.projeto.operations.infrastruture.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaTaskRepository extends JpaRepository<TaskEntity, UUID> {
}
