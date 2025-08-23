package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Tracing;
import com.studiozero.projeto.domain.repositories.TracingRepository;
import com.studiozero.projeto.infrastructure.entities.TracingEntity;
import com.studiozero.projeto.infrastructure.mappers.TracingEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaTracingRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TracingRepositoryImpl implements TracingRepository {
    private final JpaTracingRepository jpaTracingRepository;

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
