package com.studiozero.projeto.operations.domain.repositories;

import com.studiozero.projeto.operations.domain.entities.Tracing;

import java.util.List;

public interface TracingRepository {
    Tracing findTopByOrderByIdDesc();

    void deleteAllByIdNot(Integer id);

    Tracing findTopByOrderByDateTimeDesc();

    Tracing findById(Integer id);

    void save(Tracing tracing);

    void deleteById(Integer id);

    List<Tracing> findAll();
}
