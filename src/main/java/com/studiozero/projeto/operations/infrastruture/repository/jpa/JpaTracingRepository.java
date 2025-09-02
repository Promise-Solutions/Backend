package com.studiozero.projeto.operations.infrastruture.repository.jpa;

import com.studiozero.projeto.operations.infrastruture.entity.TracingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTracingRepository extends JpaRepository<TracingEntity, Integer> {
    Optional<TracingEntity> findTopByOrderByIdDesc();
    void deleteAllByIdNot(Integer id);
    TracingEntity findTopByOrderByDateTimeDesc();
}
