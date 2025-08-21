package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JpaTaskRepository extends JpaRepository<Task, UUID> {
}
