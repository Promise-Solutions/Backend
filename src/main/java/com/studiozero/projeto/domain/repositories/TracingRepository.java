package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.Tracing;

import java.util.List;

public interface TracingRepository {
    Tracing findTopByOrderByIdDesc();

    void deleteAllByIdNot(Integer id);

    Tracing findTopByOrderByDateTimeDesc();

    Tracing findById(Integer id);

    void save(Tracing tracing);

    void deleteById(Integer id);

    List<Tracing> listAll();
}
