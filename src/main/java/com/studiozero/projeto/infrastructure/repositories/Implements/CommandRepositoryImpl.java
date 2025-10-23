package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.repositories.CommandRepository;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaCommandRepository;
import com.studiozero.projeto.application.enums.Status;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.studiozero.projeto.infrastructure.entities.CommandEntity;
import com.studiozero.projeto.infrastructure.mappers.CommandEntityMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        List<CommandEntity> entities =
                jpaCommandRepository.findAllByStatus(status);
        return entities.stream().map(CommandEntityMapper::toDomain).toList();
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
