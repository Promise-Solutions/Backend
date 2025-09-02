package com.studiozero.projeto.sales.domain.repository;

import com.studiozero.projeto.sales.domain.entities.Command;
import com.studiozero.projeto.shared.application.enums.Status;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CommandRepository {
    boolean existsByClientId(UUID clientId);

    boolean existsByCommandNumber(Integer commandNumber);

    List<Command> findAllByStatus(Status status);

    List<Command> findAllByClientIdAndStatus(UUID clientId, Status status);

    List<Command> findAll();

    LocalDateTime findMaxOpeningDate();

    LocalDateTime findMaxClosingDate();

    Command findById(Integer id);

    void save(Command command);

    void deleteById(Integer id);
}
