package com.studiozero.projeto.operations.infrastruture.repository.impl;

import com.studiozero.projeto.operations.domain.entities.Tracing;
import com.studiozero.projeto.operations.domain.repositories.TracingRepository;
import com.studiozero.projeto.operations.infrastruture.entity.TracingEntity;
import com.studiozero.projeto.operations.infrastruture.mapper.TracingEntityMapper;
import com.studiozero.projeto.operations.infrastruture.repository.jpa.JpaTracingRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TracingRepositoryImpl implements TracingRepository {
    private final JpaTracingRepository jpaTracingRepository;

    public TracingRepositoryImpl(JpaTracingRepository jpaTracingRepository) {
        this.jpaTracingRepository = jpaTracingRepository;
    }

    @Override
    public Tracing findTopByOrderByIdDesc() {
        return jpaTracingRepository.findTopByOrderByIdDesc()
            .map(TracingEntityMapper::toDomain)
            .orElse(null);
    }

    @Override
    public void deleteAllByIdNot(Integer id) {
        jpaTracingRepository.deleteAllByIdNot(id);
    }

    @Override
    public Tracing findTopByOrderByDateTimeDesc() {
        TracingEntity entity = jpaTracingRepository.findTopByOrderByDateTimeDesc();
        return TracingEntityMapper.toDomain(entity);
    }

    @Override
    public Tracing findById(Integer id) {
        return jpaTracingRepository.findById(id)
            .map(TracingEntityMapper::toDomain)
            .orElse(null);
    }

    @Override
    public void save(Tracing tracing) {
        TracingEntity entity = TracingEntityMapper.toEntity(tracing);
        jpaTracingRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        jpaTracingRepository.deleteById(id);
    }

    @Override
    public List<Tracing> findAll() {
        return jpaTracingRepository.findAll().stream()
            .map(TracingEntityMapper::toDomain)
            .toList();
    }
}
