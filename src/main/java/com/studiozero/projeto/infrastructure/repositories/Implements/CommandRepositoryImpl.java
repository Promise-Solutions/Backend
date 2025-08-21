package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.repositories.CommandRepository;
import com.studiozero.projeto.infrastructure.repositories.JpaCommandRepository;
import com.studiozero.projeto.application.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CommandRepositoryImpl implements CommandRepository {
    private final JpaCommandRepository jpaCommandRepository;

    @Autowired
    public CommandRepositoryImpl(JpaCommandRepository jpaCommandRepository) {
        this.jpaCommandRepository = jpaCommandRepository;
    }

    @Override
    public boolean existsByClientId(UUID clientId) {
        // Not present in JpaCommandRepository, so use findAll and check
        return jpaCommandRepository.findAll().stream()
                .anyMatch(c -> c.getClient() != null && clientId.equals(c.getClient().getId()));
    }

    @Override
    public List<Command> findAllByStatus(Status status) {
        return jpaCommandRepository.findAllByStatus(status);
    }

    @Override
    public List<Command> findAllByClientIdAndStatus(UUID clientId, Status status) {
        return jpaCommandRepository.findAllByClientIdAndStatus(clientId, status);
    }

    @Override
    public LocalDateTime findMaxOpeningDate() {
        // Not present in JpaCommandRepository, so use findAll and get max
        return jpaCommandRepository.findAll().stream()
                .map(Command::getOpeningDateTime)
                .filter(java.util.Objects::nonNull)
                .max(LocalDateTime::compareTo)
                .orElse(null);
    }

    @Override
    public LocalDateTime findMaxClosingDate() {
        // Not present in JpaCommandRepository, so use findAll and get max
        return jpaCommandRepository.findAll().stream()
                .map(Command::getClosingDateTime)
                .filter(java.util.Objects::nonNull)
                .max(LocalDateTime::compareTo)
                .orElse(null);
    }

    @Override
    public Command findById(Integer id) {
        Optional<Command> command = jpaCommandRepository.findById(id);
        return command.orElse(null);
    }

    @Override
    public void save(Command command) {
        jpaCommandRepository.save(command);
    }

    @Override
    public void deleteById(Integer id) {
        jpaCommandRepository.deleteById(id);
    }
}
