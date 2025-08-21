package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Tracing;
import com.studiozero.projeto.domain.repositories.TracingRepository;
import com.studiozero.projeto.infrastructure.repositories.JpaTracingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TracingRepositoryImpl implements TracingRepository {
    private final JpaTracingRepository jpaTracingRepository;

    @Autowired
    public TracingRepositoryImpl(JpaTracingRepository jpaTracingRepository) {
        this.jpaTracingRepository = jpaTracingRepository;
    }

    @Override
    public Tracing findTopByOrderByIdDesc() {
        return jpaTracingRepository.findTopByOrderByIdDesc().orElse(null);
    }

    @Override
    public void deleteAllByIdNot(Integer id) {
        jpaTracingRepository.deleteAllByIdNot(id);
    }

    @Override
    public Tracing findTopByOrderByDateTimeDesc() {
        return jpaTracingRepository.findTopByOrderByDateTimeDesc();
    }

    @Override
    public Tracing findById(Integer id) {
        return jpaTracingRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Tracing tracing) {
        jpaTracingRepository.save(tracing);
    }

    @Override
    public void deleteById(Integer id) {
        jpaTracingRepository.deleteById(id);
    }

    @Override
    public List<Tracing> listAll() {
        return jpaTracingRepository.findAll();
    }
}
