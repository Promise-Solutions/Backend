package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.infrastructure.entities.TracingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JpaTracingRepository extends JpaRepository<TracingEntity, Integer> {
    Optional<TracingEntity> findTopByOrderByIdDesc();
    void deleteAllByIdNot(Integer id);
    TracingEntity findTopByOrderByDateTimeDesc();
}
