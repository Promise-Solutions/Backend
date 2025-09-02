package com.studiozero.projeto.sales.infrastruture.repository.impl;

import com.studiozero.projeto.sales.domain.entities.Command;
import com.studiozero.projeto.sales.domain.repository.CommandRepository;
import com.studiozero.projeto.sales.infrastruture.repository.jpa.JpaCommandRepository;
import com.studiozero.projeto.shared.application.enums.Status;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.studiozero.projeto.sales.infrastruture.entity.CommandEntity;
import com.studiozero.projeto.sales.infrastruture.mapper.CommandEntityMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CommandRepositoryImpl implements CommandRepository {
    private final JpaCommandRepository jpaCommandRepository;

    public CommandRepositoryImpl(JpaCommandRepository jpaCommandRepository) {
        this.jpaCommandRepository = jpaCommandRepository;
    }

    @Override
    public boolean existsByClientId(UUID clientId) {
        return jpaCommandRepository.findAll().stream()
                .anyMatch(c -> c.getClient() != null && clientId.equals(c.getClient().getId()));
    }

    @Override
    public List<Command> findAll() {
        return jpaCommandRepository.findAll().stream()
            .map(CommandEntityMapper::toDomain)
            .toList();
    }

    @Override
    public boolean existsByCommandNumber(Integer commandNumber) {
        return jpaCommandRepository.findAll().stream()
                .anyMatch(c -> commandNumber.equals(c.getCommandNumber()));
    }

    @Override
    public List<Command> findAllByStatus(Status status) {
        return jpaCommandRepository.findAllByStatus(status).stream()
            .map(CommandEntityMapper::toDomain)
            .toList();
    }

    @Override
    public List<Command> findAllByClientIdAndStatus(UUID clientId, Status status) {
        return jpaCommandRepository.findAllByClientIdAndStatus(clientId, status).stream()
            .map(CommandEntityMapper::toDomain)
            .toList();
    }

    @Override
    public LocalDateTime findMaxOpeningDate() {
        return jpaCommandRepository.findAll().stream()
            .map(CommandEntityMapper::toDomain)
            .map(Command::getOpeningDateTime)
            .filter(java.util.Objects::nonNull)
            .max(LocalDateTime::compareTo)
            .orElse(null);
    }

    @Override
    public LocalDateTime findMaxClosingDate() {
        return jpaCommandRepository.findAll().stream()
            .map(CommandEntityMapper::toDomain)
            .map(Command::getClosingDateTime)
            .filter(java.util.Objects::nonNull)
            .max(LocalDateTime::compareTo)
            .orElse(null);
    }

    @Override
    public Command findById(Integer id) {
        return jpaCommandRepository.findById(id)
            .map(CommandEntityMapper::toDomain)
            .orElse(null);
    }

    @Override
    public void save(Command command) {
        CommandEntity entity = CommandEntityMapper.toEntity(command);
        jpaCommandRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        jpaCommandRepository.deleteById(id);
    }
}
