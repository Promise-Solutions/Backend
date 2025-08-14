package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.Tracing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TracingRepository extends JpaRepository<Tracing, Integer> {
    Optional<Tracing> findTopByOrderByIdDesc();

    void deleteAllByIdNot(Integer id);

    Tracing findTopByOrderByDateTimeDesc();
}
