package com.studiozero.projeto.infrastructure.repositories.jpa;

import com.studiozero.projeto.infrastructure.entities.TracingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTracingRepository extends JpaRepository<TracingEntity, Integer> {
    Optional<TracingEntity> findTopByOrderByIdDesc();
    void deleteAllByIdNot(Integer id);
    TracingEntity findTopByOrderByDateTimeDesc();
}
