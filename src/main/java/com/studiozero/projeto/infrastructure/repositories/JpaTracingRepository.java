package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.Tracing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTracingRepository extends JpaRepository<Tracing, Integer> {
    java.util.Optional<Tracing> findTopByOrderByIdDesc();

    void deleteAllByIdNot(Integer id);

    Tracing findTopByOrderByDateTimeDesc();
}
